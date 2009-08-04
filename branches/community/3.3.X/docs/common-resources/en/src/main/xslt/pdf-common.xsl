<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		        version="2.0"
		        xmlns="http://www.w3.org/TR/xhtml1/transitional"
		        xmlns:fo="http://www.w3.org/1999/XSL/Format"
                	xmlns:jbh="java:org.jboss.highlight.renderer.FORenderer"
		        exclude-result-prefixes="jbh">

<xsl:import href="classpath:/xslt/org/jboss/pdf.xsl" />
	
<xsl:attribute-set name="book.titlepage.recto.style">
	<xsl:attribute name="font-family">
		<xsl:value-of select="$title.fontset"/>
	</xsl:attribute>
	<xsl:attribute name="color"><xsl:value-of select="$titlepage.color"/></xsl:attribute>
	<xsl:attribute name="font-weight">bold</xsl:attribute>
	<xsl:attribute name="font-size">12pt</xsl:attribute>
	<xsl:attribute name="text-align">center</xsl:attribute>
</xsl:attribute-set>

<xsl:template name="toc.line">
  <xsl:param name="toc-context" select="NOTANODE"/>  
  <xsl:variable name="id">  
    <xsl:call-template name="object.id"/>
  </xsl:variable>

  <xsl:variable name="label">  
    <xsl:apply-templates select="." mode="label.markup"/>  
  </xsl:variable>

  <fo:block xsl:use-attribute-sets="toc.line.properties">  
    <fo:inline keep-with-next.within-line="always">
      
      <fo:basic-link internal-destination="{$id}">  
        <xsl:if test="$label != ''">
          <xsl:copy-of select="$label"/>
          <xsl:value-of select="$autotoc.label.separator"/>
        </xsl:if>
        <xsl:choose>
		<xsl:when test="self::section/title/emphasis[@role='since']">
			<xsl:variable name="titleWithoutEmphasis">
				<xsl:copy-of select="self::section/title/text()" />
			</xsl:variable>
			<xsl:apply-templates select="$titleWithoutEmphasis" mode="title.markup"/>  
		</xsl:when>
		<xsl:otherwise>
			<xsl:apply-templates select="." mode="title.markup"/>  
		</xsl:otherwise>
        </xsl:choose>
      </fo:basic-link>
    </fo:inline>
    <fo:inline keep-together.within-line="always"> 
      <xsl:text> </xsl:text>
      <fo:leader leader-pattern="dots"
                 leader-pattern-width="3pt"
                 leader-alignment="reference-area"
                 keep-with-next.within-line="always"/>
      <xsl:text> </xsl:text>
      <fo:basic-link internal-destination="{$id}">
        <fo:page-number-citation ref-id="{$id}"/>
      </fo:basic-link>
    </fo:inline>
  </fo:block>
</xsl:template>

<!-- avoid bulk HTML elements generation in the PDF table -->
<xsl:template match="//emphasis[@role='arrowWrapper']" />
<xsl:template match="//emphasis[@role='descriptionWrapper']">
	<xsl:value-of select="." />
</xsl:template>
<xsl:template match="//emphasis[@role='since']">
	<fo:inline font-size="75%" vertical-align="super">
		<xsl:value-of select="." />
	</fo:inline>
</xsl:template>


	<xsl:attribute-set name="header.content.properties">
		<xsl:attribute name="font-family">Helvetica</xsl:attribute>
		<xsl:attribute name="font-size">9pt</xsl:attribute>
		<xsl:attribute name="font-weight">bold</xsl:attribute>
		
	</xsl:attribute-set>
	
	<xsl:template name="header.content">  
		<xsl:param name="pageclass" select="''"/>
		<xsl:param name="sequence" select="''"/>
		<xsl:param name="position" select="''"/>
		<xsl:param name="gentext-key" select="''"/>
		<fo:block> 
		<!--	sequence can be odd, even, first, blank
				position can be left, center, right-->
			<xsl:choose>
				
				<xsl:when test="$sequence = 'odd' and $position = 'left'">
					<xsl:apply-templates select="." 
						mode="object.title.markup"/>
				</xsl:when>
				
				<xsl:when test="$sequence = 'odd' and $position = 'center'">
					<xsl:call-template name="draft.text"/>
				</xsl:when>
				
				<xsl:when test="$sequence = 'odd' and $position = 'right'">
					
				</xsl:when>
				
				<xsl:when test="$sequence = 'even' and $position = 'left'">  
					<xsl:apply-templates select="." 
						mode="object.title.markup"/>
				</xsl:when>
				
				<xsl:when test="$sequence = 'even' and $position = 'center'">
					<xsl:call-template name="draft.text"/>
				</xsl:when>
				
				<xsl:when test="$sequence = 'even' and $position = 'right'">
					
				</xsl:when>
				
				<xsl:when test="$sequence = 'first' and $position = 'left'">
					<xsl:apply-templates select="." 
						mode="object.title.markup"/>
				</xsl:when>
				
				<xsl:when test="$sequence = 'first' and $position = 'right'">
					
				</xsl:when>
				
				<xsl:when test="$sequence = 'first' and $position = 'center'"> 
					<xsl:value-of 
						select="ancestor-or-self::book/bookinfo/corpauthor"/>
					
				</xsl:when>
				
				<xsl:when test="$sequence = 'blank' and $position = 'left'">
					<fo:page-number/>
					
				</xsl:when>
				
				<xsl:when test="$sequence = 'blank' and $position = 'center'">
					<xsl:text>This page intentionally left blank</xsl:text>
				</xsl:when>
				
				<xsl:when test="$sequence = 'blank' and $position = 'right'">
				</xsl:when>
				
			</xsl:choose>
		</fo:block>
	</xsl:template>  
	
	<xsl:template name="footer.content">
		<xsl:param name="pageclass" select="''"/>
		<xsl:param name="sequence" select="''"/>
		<xsl:param name="position" select="''"/>
		<xsl:param name="gentext-key" select="''"/>
		
		<fo:block>
			<!-- pageclass can be front, body, back -->
			<!-- sequence can be odd, even, first, blank -->
			<!-- position can be left, center, right -->
			<xsl:choose>
				<xsl:when test="($sequence = 'odd'or $sequence = 'even' or $sequence = 'blank' or $sequence = 'first') and $position = 'right'">
					<fo:page-number/>
				</xsl:when>
			</xsl:choose>
		</fo:block>
	</xsl:template>
	
   <!-- avoid page sequence  to generate blank pages after even page numbers -->
   
   <xsl:template name="force.page.count">
      <xsl:param name="element" select="local-name(.)"/>
      <xsl:param name="master-reference" select="''"/>
      <xsl:text>no-force</xsl:text>
   </xsl:template>
   
   
   <!-- adding corpauthor entry to the titlepage -->
   
   <xsl:template name="book.titlepage.recto">
      <xsl:choose>
         <xsl:when test="bookinfo/title">
            <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
               select="bookinfo/title" />
         </xsl:when>
         
         <xsl:when test="info/title">
            <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
               select="info/title" />
         </xsl:when>
         <xsl:when test="title">
            <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
               select="title" />
         </xsl:when>
      </xsl:choose>
      
      <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
         select="bookinfo/issuenum" />
      <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
         select="info/issuenum" />
      <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
         select="issuenum" />
      <xsl:apply-templates mode="book.titlepage.recto.auto.mode" select="bookinfo/corpauthor"/>
      
      <xsl:choose>
         <xsl:when test="bookinfo/subtitle">
            <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
               select="bookinfo/subtitle" />
         </xsl:when>
         <xsl:when test="info/subtitle">
            <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
               select="info/subtitle" />
         </xsl:when>
         <xsl:when test="subtitle">
            <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
               select="subtitle" />
         </xsl:when>
      </xsl:choose>
      
      <fo:block xsl:use-attribute-sets="book.titlepage.recto.style"
         font-size="14pt" space-before="15.552pt">
         <xsl:apply-templates mode="book.titlepage.recto.auto.mode"
            select="bookinfo/releaseinfo" />
      </fo:block>
      
      <fo:block text-align="center" space-before="15.552pt">
         <xsl:call-template name="person.name.list">
            <xsl:with-param name="person.list" select="bookinfo/authorgroup/author|bookinfo/authorgroup/corpauthor" />
            <xsl:with-param name="person.type" select="'author'"/>
         </xsl:call-template>
      </fo:block>
      
      <fo:block text-align="center" space-before="15.552pt">
         <xsl:call-template name="person.name.list">
            <xsl:with-param name="person.list" select="bookinfo/authorgroup/editor" />
            <xsl:with-param name="person.type" select="'editor'"/>
         </xsl:call-template>
      </fo:block>
      
      <fo:block text-align="center" space-before="15.552pt">
         <xsl:call-template name="person.name.list">
            <xsl:with-param name="person.list" select="bookinfo/authorgroup/othercredit" />
            <xsl:with-param name="person.type" select="'othercredit'"/>
         </xsl:call-template>
      </fo:block>
      
   </xsl:template>
   
   <xsl:template match="corpauthor" mode="book.titlepage.recto.mode">
      <fo:block>
         <xsl:apply-templates mode="book.titlepage.recto.mode"/>
      </fo:block>
   </xsl:template>
   
   <!-- overwriting the font-size of the subtitle on the titlepage -->
   
   <xsl:template match="subtitle" mode="book.titlepage.recto.auto.mode">
      <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" xsl:use-attribute-sets="book.titlepage.recto.style" text-align="center" font-size="18pt" space-before="2pt" font-family="{$title.fontset}">
         <xsl:apply-templates select="." mode="book.titlepage.recto.mode"/>
      </fo:block>
   </xsl:template>
	
	<!--#################################remove column-width unspecified  Warning########-->
	<xsl:template name="generate.col">
		<!-- generate the table-column for column countcol -->
		<xsl:param name="countcol">1</xsl:param>
		<xsl:param name="colspecs" select="./colspec"/>
		<xsl:param name="count">1</xsl:param>
		<xsl:param name="colnum">1</xsl:param>
		
		<xsl:choose>
			<xsl:when test="$count>count($colspecs)">
				<fo:table-column column-number="{$countcol}" column-width="proportional-column-width(1)">
					<xsl:variable name="colwidth">
						<xsl:call-template name="calc.column.width"/>
					</xsl:variable>
					<xsl:if test="$colwidth != 'proportional-column-width(1)'">
						<xsl:attribute name="column-width">
							<xsl:value-of select="$colwidth"/>
						</xsl:attribute>
					</xsl:if>
				</fo:table-column>
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="colspec" select="$colspecs[$count=position()]"/>
				
				<xsl:variable name="colspec.colnum">
					<xsl:choose>
						<xsl:when test="$colspec/@colnum">
							<xsl:value-of select="$colspec/@colnum"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="$colnum"/>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				
				<xsl:variable name="colspec.colwidth">
					<xsl:choose>
						<xsl:when test="$colspec/@colwidth">
							<xsl:value-of select="$colspec/@colwidth"/>
						</xsl:when>
						<xsl:otherwise>1*</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				
				<xsl:choose>
					<xsl:when test="$colspec.colnum=$countcol">
						<fo:table-column column-number="{$countcol}" column-width="proportional-column-width(1)">
							<xsl:variable name="colwidth">
								<xsl:call-template name="calc.column.width">
									<xsl:with-param name="colwidth">
										<xsl:value-of select="$colspec.colwidth"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:variable>
							<xsl:if test="$colwidth != 'proportional-column-width(1)'">
								<xsl:attribute name="column-width">
									<xsl:value-of select="$colwidth"/>
								</xsl:attribute>
							</xsl:if>
						</fo:table-column>
					</xsl:when>
					<xsl:otherwise>
						<xsl:call-template name="generate.col">
							<xsl:with-param name="countcol" select="$countcol"/>
							<xsl:with-param name="colspecs" select="$colspecs"/>
							<xsl:with-param name="count" select="$count+1"/>
							<xsl:with-param name="colnum">
								<xsl:choose>
									<xsl:when test="$colspec/@colnum">
										<xsl:value-of select="$colspec/@colnum + 1"/>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="$colnum + 1"/>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:with-param>
						</xsl:call-template>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
