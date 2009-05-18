/**
 * License Agreement.
 *
 * YUI Compressor Maven Mojo
 *
 * Copyright (C) 2007 Alchim31 Team
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
 
package net.sf.alchim.mojo.yuicompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.IOUtil;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * Apply compression on JS and CSS (using YUI Compressor).
 *
 * @goal compress
 * @phase process-resources
 *
 * @author David Bernard
 * @created 2007-08-28
 */
// @SuppressWarnings("unchecked")
public class YuiCompressorMojo extends MojoSupport {

    /**
     * Read the input file using "encoding".
     *
     * @parameter expression="${file.encoding}" default-value="UTF-8"
     */
    private String encoding;

    /**
     * The output filename suffix.
     *
     * @parameter expression="${maven.yuicompressor.suffix}" default-value="-min"
     */
    private String suffix;

    /**
     * If no "suffix" must be add to output filename (maven's configuration manage empty suffix like default).
     *
     * @parameter expression="${maven.yuicompressor.nosuffix}" default-value="false"
     */
    private boolean nosuffix;

    /**
     * Insert line breaks in output after the specified column number.
     *
     * @parameter expression="${maven.yuicompressor.linebreakpos}" default-value="0"
     */
    private int linebreakpos;

    /**
     * [js only] Minify only, do not obfuscate.
     *
     * @parameter expression="${maven.yuicompressor.nomunge}" default-value="false"
     */
    private boolean nomunge;

    /**
     * [js only] Preserve unnecessary semicolons.
     *
     * @parameter expression="${maven.yuicompressor.preserveAllSemiColons}" default-value="false"
     */
    private boolean preserveAllSemiColons;

    /**
     * [js only] Preserve string (no optimization).
     *
     * @parameter expression="${maven.yuicompressor.preserveStringLiterals}" default-value="false"
     */
    private boolean preserveStringLiterals;

    /**
     * force the compression of every files,
     * else if compressed file already exists and is younger than source file, nothing is done.
     *
     * @parameter expression="${maven.yuicompressor.force}" default-value="false"
     */
    private boolean force;

    /**
     * a list of aggregation/concatenation to do after processing,
     * for example to create big js files that contain several small js files.
     * Aggregation could be done on any type of file (js, css, ...).
     *
     * @parameter
     */
    private Aggregation[] aggregations;

    /**
     * request to create a gzipped version of the yuicompressed/aggregation files.
     *
     * @parameter expression="${maven.yuicompressor.gzip}" default-value="false"
     */
    private boolean gzip;

    /**
     * show statistics (compression ratio).
     *
     * @parameter expression="${maven.yuicompressor.statistics}" default-value="true"
     */
    private boolean statistics;

    private long inSizeTotal_;
    private long outSizeTotal_;

    @Override
    protected String[] getDefaultIncludes() throws Exception {
        return new String[]{"**/*.css", "**/*.js"};
    }

    @Override
    public void beforeProcess() throws Exception {
        if (nosuffix) {
            suffix = "";
        }
    }

    @Override
    protected void afterProcess() throws Exception {
        if (statistics && (inSizeTotal_ > 0)) {
            getLog().info(String.format("total input (%db) -> output (%db)[%d%%]", inSizeTotal_, outSizeTotal_, ((outSizeTotal_ * 100)/inSizeTotal_)));
        }
        if (aggregations != null) {
            for(Aggregation aggregation : aggregations) {
                getLog().info("generate aggregation : " + aggregation.output);
                aggregation.run(outputDirectory);
                File gzipped = gzipIfRequested(aggregation.output);
                if (statistics) {
                    if (gzipped != null) {
                        getLog().info(String.format("%s (%db) -> %s (%db)[%d%%]", aggregation.output.getName(), aggregation.output.length(), gzipped.getName(), gzipped.length(), ratioOfSize(aggregation.output, gzipped)));
                    } else if (aggregation.output.exists()){
                        getLog().info(String.format("%s (%db)", aggregation.output.getName(), aggregation.output.length()));
                    } else {
                        getLog().warn(String.format("%s not created", aggregation.output.getName()));
                    }
                }
            }
        }
    }


    @Override
    protected void processFile(SourceFile src) throws Exception {
        if (getLog().isDebugEnabled()) {
            getLog().debug("compress file :" + src.toFile()+ " to " + src.toDestFile(suffix));
        }
        File inFile = src.toFile();
        File outFile = src.toDestFile(suffix);

        getLog().debug("only compress if input file is youger than existing output file");
        if (!force && outFile.exists() && (outFile.lastModified() > inFile.lastModified())) {
            if (getLog().isInfoEnabled()) {
                getLog().info("nothing to do, " + outFile + " is younger than original, use 'force' option or clean your target");
            }
            return;
        }

        InputStreamReader in = null;
        OutputStreamWriter out = null;
        try {
            in = new InputStreamReader(new FileInputStream(inFile), encoding);
            if (!outFile.getParentFile().exists() && !outFile.getParentFile().mkdirs()) {
                throw new MojoExecutionException( "Cannot create resource output directory: " + outFile.getParentFile() );
            }
            getLog().debug("use a temporary outputfile (in case in == out)");
            File outFileTmp = new File(outFile.getAbsolutePath() + ".tmp");
            FileUtils.forceDelete(outFileTmp);

            getLog().debug("start compression");
            out = new OutputStreamWriter(new FileOutputStream(outFileTmp), encoding);
            if (".js".equalsIgnoreCase(src.getExtension())) {
                JavaScriptCompressor compressor = new JavaScriptCompressor(in, jsErrorReporter_);
                compressor.compress(out, linebreakpos, !nomunge, jswarn, preserveAllSemiColons, preserveStringLiterals);
            } else if (".css".equalsIgnoreCase(src.getExtension())) {
                CssCompressor compressor = new CssCompressor(in);
                compressor.compress(out, linebreakpos);
            }
            getLog().debug("end compression");
            // Close output file before rename.
            IOUtil.close(out);out=null;
            FileUtils.forceDelete(outFile);
            FileUtils.rename(outFileTmp, outFile);
        } finally {
            IOUtil.close(in);
            IOUtil.close(out);
        }
        File gzipped = gzipIfRequested(outFile);
        if (statistics) {
            inSizeTotal_ += inFile.length();
            outSizeTotal_ += outFile.length();
            getLog().info(String.format("%s (%db) -> %s (%db)[%d%%]", inFile.getName(), inFile.length(), outFile.getName(), outFile.length(), ratioOfSize(inFile, outFile)));
            if (gzipped != null) {
                getLog().info(String.format("%s (%db) -> %s (%db)[%d%%]", inFile.getName(), inFile.length(), gzipped.getName(), gzipped.length(), ratioOfSize(inFile, gzipped)));
            }
        }
    }

    protected File gzipIfRequested(File file) throws Exception {
        if (!gzip || (file == null) || (!file.exists())) {
            return null;
        }
        if (".gz".equalsIgnoreCase(FileUtils.getExtension(file.getName()))) {
            return null;
        }
        File gzipped = new File(file.getAbsolutePath()+".gz");
        getLog().debug(String.format("create gzip version : %s", gzipped.getName()));
        GZIPOutputStream out = null;
        FileInputStream in = null;
        try {
            out = new GZIPOutputStream(new FileOutputStream(gzipped));
            in = new FileInputStream(file);
            IOUtil.copy(in, out);
        } finally {
            IOUtil.close(in);
            IOUtil.close(out);
        }
        return gzipped;
    }

    protected long ratioOfSize(File file100, File fileX) throws Exception {
        long v100 = Math.max(file100.length(), 1);
        long vX = Math.max(fileX.length(), 1);
        return (vX * 100)/v100;
    }
}
