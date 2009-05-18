/*
 * Helma License Notice
 *
 * The contents of this file are subject to the Helma License
 * Version 2.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://adele.helma.org/download/helma/license.txt
 *
 * Copyright 1998-2003 Helma Software. All Rights Reserved.
 *
 * $RCSfile: DiffusionFilterOp.java,v $
 * $Author: alexsmirnov $
 * $Revision: 1.1.2.1 $
 * $Date: 2007/01/09 18:59:06 $
 */

/*
 * Code from com.jhlabs.image.DiffusionFilter, Java Image Processing
 * Copyright (C) Jerry Huxtable 1998
 * http://www.jhlabs.com/ip/
 * 
 * Conversion to a BufferedImageOp inspired by:
 * http://www.peter-cockerell.net:8080/java/FloydSteinberg/FloydSteinbergFilterOp.java
 * 
 */

package org.ajax4jsf.resource.image;

import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.IndexColorModel;

import org.ajax4jsf.Messages;

public class DiffusionFilterOp implements BufferedImageOp {
	
	protected final static int[] diffusionMatrix = {
		 0, 0, 0,
		 0, 0, 7,
		 3, 5, 1,
	};

	private int[] matrix;
    private int sum;
    private boolean serpentine = true;
    private int[] colorMap;

    /**
     * Construct a DiffusionFilter
     */
    public DiffusionFilterOp() {
        setMatrix(diffusionMatrix);
    }

    /**
     * Set whether to use a serpentine pattern for return or not. This can reduce 'avalanche' artifacts in the output.
     * @param serpentine true to use serpentine pattern
     */
    public void setSerpentine(boolean serpentine) {
        this.serpentine = serpentine;
    }

    /**
     * Return the serpentine setting
     * @return the current setting
     */
    public boolean getSerpentine() {
        return serpentine;
    }

    public void setMatrix(int[] matrix) {
        this.matrix = matrix;
        sum = 0;
        for (int i = 0; i < matrix.length; i++)
            sum += matrix[i];
    }

    public int[] getMatrix() {
        return matrix;
    }

    /**
     * Do the filter operation
     * 
     * @param src The source BufferedImage. Can be any type.
     * @param dst The destination image. If not null, must be of type TYPE_BYTE_INDEXED
     * @return A dithered version of src in a BufferedImage of type TYPE_BYTE_INDEXED
     */
    public BufferedImage filter(BufferedImage src, BufferedImage dst) {

        // If there's no dest. create one
        if (dst == null)
            dst = createCompatibleDestImage(src, null);

        // Otherwise check that the provided dest is an indexed image
        else if (dst.getType() != BufferedImage.TYPE_BYTE_INDEXED) {
            throw new IllegalArgumentException(Messages.getMessage(Messages.BUFFER_TYPE_ERROR));
        }

        DataBufferByte dstBuffer = (DataBufferByte) dst.getRaster().getDataBuffer();
        byte dstData[] = dstBuffer.getData();

        // Other things to test are pixel bit strides, scanline stride and transfer type
        // Same goes for the source image

        IndexColorModel icm = (IndexColorModel) dst.getColorModel();
        colorMap = new int[icm.getMapSize()];
        icm.getRGBs(colorMap);

        int width = src.getWidth();
        int height = src.getHeight();

        DataBufferInt srcBuffer = (DataBufferInt) src.getRaster().getDataBuffer();
        int srcData[] = srcBuffer.getData();

        // This is the offset into the buffer of the current source pixel
        int index = 0;

        // Loop through each pixel
        for (int y = 0; y < height; y++) {
            boolean reverse = serpentine && (y & 1) == 1;
            int direction;
            if (reverse) {
                index = y * width + width - 1;
                direction = -1;
            } else {
                index = y * width;
                direction = 1;
            }
            for (int x = 0; x < width; x++) {
                int rgb1 = srcData[index];
                int a1 = (rgb1 >> 24) & 0xff;
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = rgb1 & 0xff;

                int idx = findIndex(r1, g1, b1, a1);
                dstData[index] = (byte) idx;

                int rgb2 = colorMap[idx];
                int a2 = (rgb2 >> 24) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = rgb2 & 0xff;

                int er = r1 - r2;
                int eg = g1 - g2;
                int eb = b1 - b2;
                int ea = a1 - a2;

                for (int i = -1; i <= 1; i++) {
                    int iy = i + y;
                    if (0 <= iy && iy < height) {
                        for (int j = -1; j <= 1; j++) {
                            int jx = j + x;
                            if (0 <= jx && jx < width) {
                                int w;
                                if (reverse)
                                    w = matrix[(i + 1) * 3 - j + 1];
                                else
                                    w = matrix[(i + 1) * 3 + j + 1];
                                if (w != 0) {
                                    int k = reverse ? index - j : index + j;
                                    rgb1 = srcData[k];
                                    a1 = ((rgb1 >> 24) & 0xff) + ea * w / sum;
                                    r1 = ((rgb1 >> 16) & 0xff) + er * w / sum;
                                    g1 = ((rgb1 >> 8) & 0xff) + eg * w / sum;
                                    b1 = (rgb1 & 0xff) + eb * w / sum;

                                    srcData[k] = ((clamp(a1) << 24) | clamp(r1) << 16)
                                        | (clamp(g1) << 8) | clamp(b1);
                                }
                            }
                        }
                    }
                }
                index += direction;
            }
        }

        return dst;
    }

    private static int clamp(int c) {
        if (c < 0)
            return 0;
        if (c > 255)
            return 255;
        return c;
    }

    int findIndex(int r1, int g1, int b1, int a1)
        throws ArrayIndexOutOfBoundsException {
        int idx = 0;
        long dist = Long.MAX_VALUE;
        for (int i = 0; i < colorMap.length; i++) {
            int rgb2 = colorMap[i];
            int a2 = (rgb2 >> 24) & 0xff;
            int r2 = (rgb2 >> 16) & 0xff;
            int g2 = (rgb2 >> 8) & 0xff;
            int b2 = rgb2 & 0xff;

            int da = a1 - a2;
            int dr = r1 - r2;
            int dg = g1 - g2;
            int db = b1 - b2;

            long newdist = da * da + dr * dr + dg * dg + db * db;
            if (newdist < dist) {
                idx = i;
                dist = newdist;
            }
        }
        return idx;
    }

    // This always returns an indexed image
    public BufferedImage createCompatibleDestImage(BufferedImage src,
        ColorModel destCM) {
        return new BufferedImage(src.getWidth(), src.getHeight(),
            BufferedImage.TYPE_BYTE_INDEXED);
    }

    // There are no rendering hints
    public RenderingHints getRenderingHints() {
        return null;
    }

    // No transformation, so return the source point
    public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
        if (dstPt == null)
            dstPt = new Point2D.Float();
        dstPt.setLocation(srcPt.getX(), srcPt.getY());
        return dstPt;
    }

    // No transformation, so return the source bounds
    public Rectangle2D getBounds2D(BufferedImage src) {
        return src.getRaster().getBounds();
    }
}