<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:javaee="http://java.sun.com/xml/ns/javaee"
	xmlns:u="http:/jsf.exadel.com/template/util"
	xmlns:f='http:/jsf.exadel.com/template'
	version="2.0" exclude-result-prefixes="javaee u f">
	<xsl:output name="xmlOutput" method="xml" version="1.0" encoding="UTF-8" indent="yes"
		omit-xml-declaration="yes" />
	<xsl:param name="lang" />
	<xsl:param name="title" />
	<xsl:param name="separator" />
	<xsl:param name="prefix" />
	<xsl:param name="xcssPath" />
	<xsl:param name="outputDir" />
	
	<xsl:variable name="rowsAmount">
		<xsl:value-of select="count(.)" />
	</xsl:variable>
	
	<!--xsl:template match="//f:template">
		<table>
			<xsl:for-each select="u:selector">
				<xsl:sort select="@name" />
				<xsl:if test="u:style[@skin]">
					<row>
						<entry  morerows='$rowsAmount' valign='middle'>
							<xsl:value-of select="@name"/>
						</entry>
					</row>
					<xsl:for-each select="u:style">	
						<row>
							<entry>
								<xsl:value-of select="@skin"/>
							</entry>
							<entry>
								<xsl:value-of select="@name"/>
							</entry>
						</row>
				</xsl:for-each>
				</xsl:if>
			</xsl:for-each>
		</table>
	</xsl:template>
	
	<xsl:template match="/f:template/f:verbatim"/-->
	
	
	
	<xsl:template match="javaee:taglib | taglib">
		<xsl:variable name="excluded-tag-names">
			header2,header3,header4,header5,header6
		</xsl:variable>

		<xsl:for-each select="javaee:tag | tag">

			<!--xsl:value-of select="./name/text()" /-->

			<xsl:if test="not(contains($excluded-tag-names, javaee:name))">
				<xsl:call-template name="tag" />
			</xsl:if>
			<xsl:if	test="not(contains($excluded-tag-names, ./name/text()))">
					<xsl:call-template name="tag" />
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="tag">
	
	<xsl:variable name="tag_name">
		<xsl:choose>
			<xsl:when test="javaee:name">
				<xsl:value-of select="javaee:name" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="./name/text()" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
	
	<xsl:result-document href="{concat('file:///', $outputDir, $prefix, '_', $tag_name, '.xml')}" format="xmlOutput">		
		<section role="NotInToc">
			
			<!--
			<xsl:variable name="prefix">
				<xsl:choose>
					<xsl:when test="/javaee:taglib/javaee:short-name">
						<xsl:value-of select="/javaee:taglib/javaee:short-name" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="/taglib/short-name/text()" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>			
			-->
			<xsl:attribute name="id">
				<xsl:value-of select="concat($prefix, '_', $tag_name)" />
			</xsl:attribute>
			<title>
				&lt;
				<xsl:value-of select="concat($prefix,':', $tag_name)" />
				&gt;
				<emphasis role="since">
					<superscript>
						<xsl:choose>
							<xsl:when test="document(concat($lang, $separator, 'included', $separator, $prefix, '_', $tag_name, '.desc.xml'))/section/sectioninfo/releaseinfo">
								<xsl:text> available since </xsl:text>
								<emphasis role="version">
									<xsl:value-of select="document(concat($lang, $separator, 'included', $separator, $prefix, '_', $tag_name, '.desc.xml'))/section/sectioninfo/releaseinfo"/>					
								</emphasis>
							</xsl:when>
							<xsl:when test="document(concat($lang, $separator,'included',$separator,$tag_name, '.desc.xml'))/section/sectioninfo/releaseinfo">
								<xsl:text> available since </xsl:text>
								<emphasis role="version">
									<xsl:value-of select="document(concat($lang, $separator,'included',$separator,$tag_name, '.desc.xml'))/section/sectioninfo/releaseinfo"/>					
								</emphasis>
							</xsl:when>
							<xsl:otherwise>
								<xsl:text> available since </xsl:text>
								<emphasis role="version">
									<xsl:text>3.0.0</xsl:text>
								</emphasis>			
							</xsl:otherwise>
						</xsl:choose>
					</superscript>
				</emphasis>
			</title>
			<xsl:call-template name="fileMerge">
				<xsl:with-param name="tag_name" select="$tag_name" />
				<xsl:with-param name="filePostfix" select="'.desc'"/>
			</xsl:call-template>
			
			
			<xsl:variable name="html-attributes">
				,accesskey,accept,accept-charset,alt,border,charset,coords,dir,disabled,hreflang,lang,maxlength,readonly,rel,rev,rows,shape,size,style,tabindex,target,title,type,width, </xsl:variable>
			
			<xsl:variable name="dhtml-attributes">
				,onblur,onchange,onclick,ondblclick,onfocus,onkeydown,onkeypress,onkeyup,onmousedown,onmousemove,onmouseout,onmouseover,onmouseup,onreset,onsubmit, </xsl:variable>
			
			<xsl:variable name="jsf-attributes">
				,id,binding,rendered,styleClass,value,valueChangeListener,converter,validator,required,columnClasses,rowClasses,</xsl:variable>
			
			<xsl:variable name="onselect-custom-attribute">
				,menuItem,colorPicker,suggestionbox, </xsl:variable>
			
			<table>
				<title>
					<xsl:value-of select="$prefix" />
					:
					<xsl:value-of select="javaee:name" />
					<xsl:value-of select="name" />
					attributes
				</title>
				<tgroup cols="2">
					<thead>
						<row>
							<entry>Attribute Name</entry>
							<entry>
								<emphasis role="descriptionWrapper">Description</emphasis>
								<emphasis role="arrowWrapper">
									<ulink url="javascript:void(0);" role="html">
										<inlinegraphic fileref="images/minus.gif" />
									</ulink>
								</emphasis>
							</entry>
						</row>
					</thead>
					<tbody>
						<xsl:for-each select="javaee:attribute | attribute">
							<xsl:sort select="javaee:name|name" />
							<row>
								<entry>
									<xsl:value-of select="javaee:name"/>
									<xsl:value-of select="name"/>
								</entry>
								<entry>
									<xsl:choose>
										<xsl:when
											test="contains($html-attributes, concat(',',javaee:name|name,','))"
											>HTML: <xsl:value-of select="javaee:description"
												disable-output-escaping="yes"/>
											<xsl:value-of select="description"
												disable-output-escaping="yes"/>
										</xsl:when>
										<xsl:when test="contains($dhtml-attributes, concat(',',javaee:name|name,','))">
											DHTML: 
											<xsl:value-of select="javaee:description"
												disable-output-escaping="yes"/>
											<xsl:value-of select="description"
												disable-output-escaping="yes"/>
										</xsl:when>
										<xsl:when test="contains('onselect', javaee:name|name) and not(contains($onselect-custom-attribute, concat(',',$tag_name,',')))">
											DHTML: 
											<xsl:value-of select="javaee:description"
												disable-output-escaping="yes"/>
											<xsl:value-of select="description"
												disable-output-escaping="yes"/>
										</xsl:when>
										<xsl:when 
											test="contains($jsf-attributes, concat(',',javaee:name|name,','))"
											> JSF: <xsl:value-of select="javaee:description"
												disable-output-escaping="yes"/>
											<xsl:value-of select="description"
												disable-output-escaping="yes"/>
										</xsl:when>
										<xsl:otherwise>
											<xsl:value-of select="javaee:description"
												disable-output-escaping="yes"/>
											<xsl:value-of select="description"
												disable-output-escaping="yes"/>
										</xsl:otherwise>
									</xsl:choose>
								</entry>
							</row>
						</xsl:for-each>
					</tbody>
				</tgroup>
			</table>

			<!--xsl:if test="$prefix != 'a4j'">
				<table>
					<title>
						<xsl:value-of select="$prefix" />
						:
						<xsl:value-of select="javaee:name" />
						<xsl:value-of select="name" />
						classes
					</title>
					<tgroup cols="3">
						<thead>
							<row>
								<entry>Class Name</entry>
								<entry>Skin parameters</entry>
								<entry>CSS properties</entry>
								
							</row>
						</thead>
						<tbody>
					<xsl:for-each select="document(concat($xcssPath, $separator, $tag_name, '.xml'))/*">
						<xsl:copy-of select="*" />
					</xsl:for-each>
						</tbody>
					</tgroup>
				</table>
			</xsl:if-->
			
			<xsl:call-template name="fileMerge">
				<xsl:with-param name="tag_name" select="$tag_name" />
			</xsl:call-template>
		</section>
</xsl:result-document>		
	</xsl:template>
	<xsl:template name="fileMerge" xmlns:file="java.io.File">
		<xsl:param name="filePostfix" select="''"/>
		<xsl:param name="tag_name" select="''"/>
		<xsl:choose>
			<xsl:when test="doc-available(concat($lang, $separator,'included',$separator, $prefix, '_', $tag_name, $filePostfix, '.xml'))">
				<xsl:for-each select="document(concat($lang, $separator,'included',$separator, $prefix, '_', $tag_name, $filePostfix, '.xml'))/*">
					<xsl:copy-of select="./*" />
				</xsl:for-each>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="document(concat($lang, $separator,'included',$separator,$tag_name,$filePostfix, '.xml'))/*">
					<xsl:copy-of select="./*" />
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template> 
</xsl:transform>
