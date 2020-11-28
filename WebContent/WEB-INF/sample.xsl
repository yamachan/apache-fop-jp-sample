<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
	<xsl:output encoding="UTF-8" indent="yes" method="xml"
		standalone="no" omit-xml-declaration="no" />
	<xsl:template match="users-data">
		<fo:root language="ja">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4"
					page-height="29.7cm" page-width="21cm" margin-top="2cm"
					margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simpleA4" font-family="Meiryo,Hiragino">
				<fo:flow flow-name="xsl-region-body">
					<fo:block text-align="center" margin-bottom="10mm">
						ヘッダー部分
					</fo:block>
					<fo:block text-align="right" margin-bottom="5mm">
						Date: <xsl:value-of select="date" />
					</fo:block>
					<fo:block margin-left="20mm">
						Client name: <xsl:value-of select="cname" />
					</fo:block>
					<fo:block margin-left="20mm" margin-bottom="5mm">
						Client age: <xsl:value-of select="cage" />
					</fo:block>
					<fo:block>
						<xsl:value-of select="body" />
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>