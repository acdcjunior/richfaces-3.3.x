<?xml version='1.0'?>

<!--
   Copyright 2008 JBoss, a division of Red Hat
   License: LGPL
   Author: Mark Newton <mark.newton@jboss.org>
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:date="http://exslt.org/dates-and-times" exclude-result-prefixes="date">

   <xsl:import href="collapsing-navigation.xsl"/>
   <!--xsl:param name="generate.toc" select="'book toc'"/-->
   <xsl:param name="toc.section.depth" select="5"/>
   <xsl:param name="generate.section.toc.level" select="3"/>
   
   <xsl:param name="use.id.as.filename" select="1"/>
   <xsl:param name="generate.toc">
book      toc
chapter   toc
sect1     toc
sect2     toc
sect3     toc
sect4     toc
sect5     toc
section   toc
</xsl:param>
	

<xsl:template name="make.toc">
  <xsl:param name="toc-context" select="."/>
  <xsl:param name="toc.title.p" select="true()"/>
  <xsl:param name="nodes" select="/NOT-AN-ELEMENT"/>

  <xsl:variable name="nodes.plus" select="$nodes | qandaset"/>

  <xsl:variable name="toc.title">
    <xsl:if test="$toc.title.p">
      <p>
        <b>
          <xsl:call-template name="gentext">
            <xsl:with-param name="key">TableofContents</xsl:with-param>
          </xsl:call-template>
        </b>
      </p>
    </xsl:if>
  </xsl:variable>

  <xsl:choose>
    <xsl:when test="$manual.toc != ''">
      <xsl:variable name="id">
        <xsl:call-template name="object.id"/>
      </xsl:variable>
      <xsl:variable name="toc" select="document($manual.toc, .)"/>
      <xsl:variable name="tocentry" select="$toc//tocentry[@linkend=$id]"/>
      <xsl:if test="$tocentry and $tocentry/*">
      <a href="javascript:void(0);" id="expand_collapse">expand all</a>
        <div class="toc">
          <xsl:copy-of select="$toc.title"/>
          <xsl:element name="{$toc.list.type}" namespace="http://www.w3.org/1999/xhtml">
            <xsl:call-template name="manual-toc">
              <xsl:with-param name="tocentry" select="$tocentry/*[1]"/>
            </xsl:call-template>
          </xsl:element>
        </div>
      </xsl:if>
    </xsl:when>
    <xsl:otherwise>
      <xsl:choose>
        <xsl:when test="$qanda.in.toc != 0">
          <xsl:if test="$nodes.plus">
            <a href="javascript:void(0);" id="expand_collapse">expand all</a>
            <div class="toc">
              <xsl:copy-of select="$toc.title"/>
              <xsl:element name="{$toc.list.type}" namespace="http://www.w3.org/1999/xhtml">
                <xsl:apply-templates select="$nodes.plus" mode="toc">
                  <xsl:with-param name="toc-context" select="$toc-context"/>
                </xsl:apply-templates>
              </xsl:element>
            </div>
          </xsl:if>
        </xsl:when>
        <xsl:otherwise>
          <xsl:if test="$nodes">
          <a href="javascript:void(0);" id="expand_collapse">expand all</a>
            <div class="toc">
              <xsl:copy-of select="$toc.title"/>
              <xsl:element name="{$toc.list.type}" namespace="http://www.w3.org/1999/xhtml">
                <xsl:apply-templates select="$nodes" mode="toc">
                  <xsl:with-param name="toc-context" select="$toc-context"/>
                </xsl:apply-templates>
              </xsl:element>
            </div>
          </xsl:if>
        </xsl:otherwise>
      </xsl:choose>

    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

   
<xsl:template name="toc.line">
  <xsl:param name="toc-context" select="."/>
  <xsl:param name="depth" select="1"/>
  <xsl:param name="depth.from.context" select="8"/>
  <xsl:param name="autotoc.label.in.hyperlink" select="1" />

 <span>
  <xsl:attribute name="class"><xsl:value-of select="local-name(.)"/></xsl:attribute>

  <!-- * if $autotoc.label.in.hyperlink is zero, then output the label -->
  <!-- * before the hyperlinked title (as the DSSSL stylesheet does) -->
  <xsl:if test="$autotoc.label.in.hyperlink = 0">
    <xsl:variable name="label">
      <xsl:apply-templates select="." mode="label.markup"/>
    </xsl:variable>
    <xsl:copy-of select="$label"/>
    <xsl:if test="$label != ''">
      <xsl:value-of select="$autotoc.label.separator"/>
    </xsl:if>
  </xsl:if>
  <a>
    <xsl:attribute name="href">
      <xsl:call-template name="href.target">
        <xsl:with-param name="context" select="$toc-context"/>
        <xsl:with-param name="toc-context" select="$toc-context"/>
      </xsl:call-template>
    </xsl:attribute>

<!--xsl:choose>
<xsl:when test="@role='new'">
<xsl:attribute name="class">
<xsl:value-of select="@role"/>
</xsl:attribute>
</xsl:when>
<xsl:when test="@role='updated'">
<xsl:attribute name="class">
<xsl:value-of select="@role"/>
</xsl:attribute>
</xsl:when>
</xsl:choose-->
    
  <!-- * if $autotoc.label.in.hyperlink is non-zero, then output the label -->
  <!-- * as part of the hyperlinked title -->
  <xsl:if test="not($autotoc.label.in.hyperlink = 0)">
    <xsl:variable name="label">
      <xsl:apply-templates select="." mode="label.markup"/>
    </xsl:variable>
    <xsl:copy-of select="$label"/>
    <xsl:if test="$label != ''">
      <xsl:value-of select="$autotoc.label.separator"/>
    </xsl:if>
  </xsl:if>

    
    <xsl:apply-templates select="." mode="titleabbrev.markup"/>

  </a>
  </span>
</xsl:template>

<!--xsl:template match="book" mode="toc">
  <xsl:param name="toc-context" select="."/>

  <xsl:call-template name="subtoc">
    <xsl:with-param name="toc-context" select="$toc-context"/>
    <xsl:with-param name="nodes" select="part|reference |preface|chapter|appendix |article |bibliography|glossary|index |refentry |bridgehead[$bridgehead.in.toc != 0]"/>
  </xsl:call-template>
</xsl:template-->


<!--xsl:template name="since" match="//emphasis[@role='since']" /-->


<xsl:template name="head.content">
	<xsl:param name="node" select="."/>
	<xsl:param name="title">
		<xsl:apply-templates select="$node" mode="object.title.markup.textonly"/>
	</xsl:param>

	<title xmlns="http://www.w3.org/1999/xhtml" >
		<xsl:copy-of select="$title"/>
	</title>

	<xsl:if test="$html.stylesheet != ''">
		<xsl:call-template name="output.html.stylesheets">
			<xsl:with-param name="stylesheets" select="normalize-space($html.stylesheet)"/>
		</xsl:call-template>
	</xsl:if>

	<xsl:if test="$link.mailto.url != ''">
		<link rev="made" href="{$link.mailto.url}"/>
	</xsl:if>

	<xsl:if test="$html.base != ''">
		<base href="{$html.base}"/>
	</xsl:if>

	<meta xmlns="http://www.w3.org/1999/xhtml" name="generator" content="DocBook {$DistroTitle} V{$VERSION}"/>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<xsl:if test="$generate.meta.abstract != 0">
		<xsl:variable name="info" select="(articleinfo |bookinfo |prefaceinfo |chapterinfo |appendixinfo |sectioninfo |sect1info |sect2info |sect3info |sect4info |sect5info |referenceinfo |refentryinfo |partinfo |info |docinfo)[1]"/>
		<xsl:if test="$info and $info/abstract">
			<meta xmlns="http://www.w3.org/1999/xhtml" name="description">
				<xsl:attribute name="content">
					<xsl:for-each select="$info/abstract[1]/*">
						<xsl:value-of select="normalize-space(.)"/>
						<xsl:if test="position() &lt; last()">
							<xsl:text> </xsl:text>
						</xsl:if>
					</xsl:for-each>
				</xsl:attribute>
			</meta>
		</xsl:if>
	</xsl:if>
	
	<link rel="shortcut icon"  type="image/vnd.microsoft.icon" href="images/favicon.ico" />

	<xsl:apply-templates select="." mode="head.keywords.content"/>
		<!--script type="text/javascript" src="script/prototype-1.6.0.2.js"><xsl:comment>If you see this message, your web browser doesn't support JavaScript or JavaScript is disabled.</xsl:comment></script>
		<script type="text/javascript" src="script/effects.js"><xsl:comment>If you see this message, your web browser doesn't support JavaScript or JavaScript is disabled.</xsl:comment></script>
		<script type="text/javascript" src="script/scriptaculous.js"><xsl:comment>If you see this message, your web browser doesn't support JavaScript or JavaScript is disabled.</xsl:comment></script-->

</xsl:template>

<xsl:template match="abstract" mode="titlepage.mode">
	<div id="timestamp">
		<xsl:text>Last published: </xsl:text>
		<xsl:call-template name="datetime.format">
			<xsl:with-param name="date" select="date:date-time()"/>
			<xsl:with-param name="format" select="'B d, Y'"/>
		</xsl:call-template>
	</div>
	<div>
	    <xsl:apply-templates select="." mode="class.attribute"/>
	    <xsl:apply-templates mode="titlepage.mode"/>
	</div>
</xsl:template>
<xsl:template name="feedback">
   <!--[if IE 6]><iframe frameborder="0" class="problemLayer" id="place"><xsl:text> </xsl:text></iframe><![endif]-->
	<div class="time_out_div" id="timeOutDiv"><xsl:text> </xsl:text></div>
	<div id="feedback-maincontainer">
		<h3 id="feedback-header">
			Create new RichFaces Documentation Jira issue
			<a href="javascript:void(0);" onclick="hidePopup('feedback-maincontainer', 'feedback-mailform', 'feedback-iFrame','feedback-submit', 'feedback-maincontainer');" id="feedback-close">
			<img src="images/close_org.png" class="feedback-images" />
		</a>
		</h3>
		<iframe id='feedback-iFrame' name="feedback-iFrame"><xsl:text> </xsl:text></iframe>
		<form onsubmit="return validate_form()" id="feedback-mailform" method="post" action="https://jira.jboss.org/jira/secure/CreateIssueDetails!init.jspa?pid=12310341&amp;issuetype=3" target="feedback-iFrame">
			<input type="hidden" id="priority" name="priority" value="3" />
			<input type="hidden" id="components" name="components" value="12311170" />
			<input type="hidden" id="versions" name="versions" value="12312451" />
			<input type="hidden" id="customfield_12310031" name="customfield_12310031" value="Documentation (Ref Guide, User Guide, etc.)" />
		
			<label for="summary">Summary</label>
			<input type="text" id="feedback-summary" name="feedback-summary" title="Summarize the subject of the issue in a few words" maxlength="255"  onKeyDown="countLeft('feedback-summary', 'left', 255);" 
			onKeyUp="countLeft('feedback-summary', 'left', 255);"/>
			<div id="summary-helper-left" class="feedback-helper">
				<span id="left">255</span> characters left
			</div>
			<div class="clear"><xsl:text> </xsl:text></div>
			<label for="feedback-description">Description</label>
			<textarea id="feedback-description" name="feedback-description" title="Provide more details about the issue" onKeyDown="countLeft('feedback-description', 'none', 500);" 
			onKeyUp="countLeft('feedback-description', 'none', 500);"><xsl:text> </xsl:text></textarea>
			<div class="clear"><xsl:text> </xsl:text></div>
			<label for="feedback-environment">Environment</label>
			<textarea id="feedback-environment" name="feedback-environment" title="Describe your environment"><xsl:text> </xsl:text></textarea>
		</form>
		<div id="guide_words">This will launch the RichFaces Jira page - to complete your feedback please login if needed, and submit the Jira.</div>
		<input type="button" id="feedback-submit" value="Proceed to Jira" name="submit" class="feedback-formbutton" title="Proceed to create new issue" onclick="fillForm('feedback-mailform'); submitForm('feedback-mailform', 'feedback-iFrame', 'feedback-submit', 'feedback-maincontainer');"/>
	</div>
	<div id="feedback-wrapper">
		<a id="feedback-link" onclick="showPopup('feedback-maincontainer');">
			<img src="images/feedback_logo.png" class="feedback-images" onload="init('feedback-summary', 'feedback-description');"/>
		</a>
	</div>							
 </xsl:template>
 
 <xsl:template name="header.navigation">
	<xsl:param name="prev" select="/foo"/>
	<xsl:param name="next" select="/foo"/>
	<xsl:param name="nav.context"/>
	<xsl:variable name="home" select="/*[1]"/>
	<xsl:variable name="up" select="parent::*"/>
	<xsl:variable name="row1" select="$navig.showtitles != 0"/>
	<xsl:variable name="row2" select="count($prev) &gt; 0 or (count($up) &gt; 0 and generate-id($up) != generate-id($home) and $navig.showtitles != 0) or count($next) &gt; 0"/>
	<xsl:if test="$suppress.navigation = '0' and $suppress.header.navigation = '0'">
		<xsl:if test="$row1 or $row2">
			<xsl:if test="$row1">
				<xsl:if test="$nightly &gt; 0">
				<div id="overlay">
					<xsl:text> </xsl:text>
				</div>
				</xsl:if>
				<!-- FEEDBACK -->
				<xsl:call-template name="feedback" />

				<p xmlns="http://www.w3.org/1999/xhtml">
					<xsl:attribute name="id">
						<xsl:text>title</xsl:text>
					</xsl:attribute>
					<a>
						<xsl:attribute name="href">
							<xsl:value-of select="$siteHref" />
						</xsl:attribute>
						<xsl:attribute name="class">
							<xsl:text>site_href</xsl:text>
						</xsl:attribute>
						<strong>
						        <xsl:value-of select="$siteLinkText"/>	
						</strong>
					</a>
					<a>
						<xsl:attribute name="href">
							<xsl:value-of select="$docHref" />
						</xsl:attribute>
						<xsl:attribute name="class">
							<xsl:text>doc_href</xsl:text>
						</xsl:attribute>
						<strong>
						        <xsl:value-of select="$docLinkText"/>	
						</strong>
					</a>
				</p>
			</xsl:if>
			<xsl:if test="$row2">
				<ul class="docnav" xmlns="http://www.w3.org/1999/xhtml">
					<li class="previous">
						<xsl:if test="count($prev)&gt;0">
							<a accesskey="p">
								<xsl:attribute name="href">
									<xsl:call-template name="href.target">
										<xsl:with-param name="object" select="$prev"/>
									</xsl:call-template>
								</xsl:attribute>
								<strong>
									<xsl:call-template name="navig.content">
										<xsl:with-param name="direction" select="'prev'"/>
									</xsl:call-template>
								</strong>
							</a>
						</xsl:if>
					</li>
					<li class="next">
						<xsl:if test="count($next)&gt;0">
							<a accesskey="n">
								<xsl:attribute name="href">
									<xsl:call-template name="href.target">
										<xsl:with-param name="object" select="$next"/>
									</xsl:call-template>
								</xsl:attribute>
								<strong>
									<xsl:call-template name="navig.content">
										<xsl:with-param name="direction" select="'next'"/>
									</xsl:call-template>
								</strong>
							</a>
						</xsl:if>
					</li>
				</ul>
			</xsl:if>
		</xsl:if>
		<xsl:if test="$header.rule != 0">
			<hr/>
		</xsl:if>
	</xsl:if>
</xsl:template>

<xsl:template name="chunk">
  <xsl:param name="node" select="."/>
  
  <xsl:choose>
    <xsl:when test="not($node/parent::*)">1</xsl:when>
    <xsl:when test="$node/parent::node()/processing-instruction('forseChanks') and local-name($node)!='title' and local-name($node)!='para' and local-name($node)='section'" >1</xsl:when>
    <xsl:when test="local-name($node) = 'sect1'
                    and $chunk.section.depth &gt;= 1
                    and ($chunk.first.sections != 0
                         or count($node/preceding-sibling::sect1) &gt; 0)">
      <xsl:text>1</xsl:text>
    </xsl:when>
    <xsl:when test="local-name($node) = 'sect2'
                    and $chunk.section.depth &gt;= 2
                    and ($chunk.first.sections != 0
                         or count($node/preceding-sibling::sect2) &gt; 0)">
      <xsl:call-template name="chunk">
        <xsl:with-param name="node" select="$node/parent::*"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:when test="local-name($node) = 'sect3'
                    and $chunk.section.depth &gt;= 3
                    and ($chunk.first.sections != 0
                         or count($node/preceding-sibling::sect3) &gt; 0)">
      <xsl:call-template name="chunk">
        <xsl:with-param name="node" select="$node/parent::*"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:when test="local-name($node) = 'sect4'
                    and $chunk.section.depth &gt;= 4
                    and ($chunk.first.sections != 0
                         or count($node/preceding-sibling::sect4) &gt; 0)">
      <xsl:call-template name="chunk">
        <xsl:with-param name="node" select="$node/parent::*"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:when test="local-name($node) = 'sect5'
                    and $chunk.section.depth &gt;= 5
                    and ($chunk.first.sections != 0
                         or count($node/preceding-sibling::sect5) &gt; 0)">
      <xsl:call-template name="chunk">
        <xsl:with-param name="node" select="$node/parent::*"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:when test="local-name($node) = 'section'
                    and $chunk.section.depth &gt;= count($node/ancestor::section)+1
                    and ($chunk.first.sections != 0
                         or count($node/preceding-sibling::section) &gt; 0)">
      <xsl:call-template name="chunk">
        <xsl:with-param name="node" select="$node/parent::*"/>
      </xsl:call-template>
    </xsl:when>

    <xsl:when test="local-name($node)='preface'">1</xsl:when>
    <xsl:when test="local-name($node)='chapter'">1</xsl:when>
    <xsl:when test="local-name($node)='appendix'">1</xsl:when>
    <xsl:when test="local-name($node)='article'">1</xsl:when>
    <xsl:when test="local-name($node)='part'">1</xsl:when>
    <xsl:when test="local-name($node)='reference'">1</xsl:when>
    <xsl:when test="local-name($node)='refentry'">1</xsl:when>
    <xsl:when test="local-name($node)='index' and ($generate.index != 0 or count($node/*) > 0)
                    and (local-name($node/parent::*) = 'article'
                    or local-name($node/parent::*) = 'book'
                    or local-name($node/parent::*) = 'part'
                    )">1</xsl:when>
    <xsl:when test="local-name($node)='bibliography'
                    and (local-name($node/parent::*) = 'article'
                    or local-name($node/parent::*) = 'book'
                    or local-name($node/parent::*) = 'part'
                    )">1</xsl:when>
    <xsl:when test="local-name($node)='glossary'
                    and (local-name($node/parent::*) = 'article'
                    or local-name($node/parent::*) = 'book'
                    or local-name($node/parent::*) = 'part'
                    )">1</xsl:when>
    <xsl:when test="local-name($node)='colophon'">1</xsl:when>
    <xsl:when test="local-name($node)='book'">1</xsl:when>
    <xsl:when test="local-name($node)='set'">1</xsl:when>
    <xsl:when test="local-name($node)='setindex'">1</xsl:when>
    <xsl:when test="local-name($node)='legalnotice'
                    and $generate.legalnotice.link != 0">1</xsl:when>
    <xsl:otherwise>0</xsl:otherwise>
  </xsl:choose>
</xsl:template>



<!-- ==================================================================== -->

<xsl:template name="navig.content">
    <xsl:param name="direction" select="next"/>
    <xsl:variable name="navtext">
        <xsl:choose>
            <xsl:when test="$direction = 'prev'">
                <xsl:call-template name="gentext.nav.prev"/>
            </xsl:when>
            <xsl:when test="$direction = 'next'">
                <xsl:call-template name="gentext.nav.next"/>
            </xsl:when>
            <xsl:when test="$direction = 'up'">
                <xsl:call-template name="gentext.nav.up"/>
            </xsl:when>
            <xsl:when test="$direction = 'home'">
                <xsl:call-template name="gentext.nav.home"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>xxx</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>

    <xsl:choose>
        <xsl:when test="$navig.graphics != 0">
            <img>
                <xsl:attribute name="src">
                    <xsl:value-of select="$navig.graphics.path"/>
                    <xsl:value-of select="$direction"/>
                    <xsl:value-of select="$navig.graphics.extension"/>
                </xsl:attribute>
                <xsl:attribute name="alt">
                    <xsl:value-of select="$navtext"/>
                </xsl:attribute>
            </img>
        </xsl:when>
        <xsl:otherwise>
            <xsl:value-of select="$navtext"/>
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>

<!-- ==================================================================== -->
</xsl:stylesheet>
