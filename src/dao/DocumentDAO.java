package dao;

import java.util.Date;

import dto.DocumentDTO;

public class DocumentDAO {
	private static DocumentDTO[] documents = {
			new DocumentDTO(new Date(0), "sample1", ""),
			new DocumentDTO(new Date(0), "sample2", ""),
			new DocumentDTO(new Date(), "sample2", ""),
	};
	private static String xslHead = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<xsl:stylesheet\r\n"
			+ "	xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"\r\n"
			+ "	xmlns:fo=\"http://www.w3.org/1999/XSL/Format\" version=\"1.0\">\r\n"
			+ "	<xsl:output encoding=\"UTF-8\" indent=\"yes\" method=\"xml\"\r\n"
			+ "		standalone=\"no\" omit-xml-declaration=\"no\" />\r\n"
			+ "	<xsl:template match=\"users-data\">\r\n"
			+ "		<fo:root language=\"ja\">\r\n"
			+ "			<fo:layout-master-set>\r\n"
			+ "				<fo:simple-page-master master-name=\"simpleA4\"\r\n"
			+ "					page-height=\"29.7cm\" page-width=\"21cm\" margin-top=\"2cm\"\r\n"
			+ "					margin-bottom=\"2cm\" margin-left=\"2cm\" margin-right=\"2cm\">\r\n"
			+ "					<fo:region-body />\r\n"
			+ "				</fo:simple-page-master>\r\n"
			+ "			</fo:layout-master-set>\r\n"
			+ "			<fo:page-sequence master-reference=\"simpleA4\" font-family=\"Meiryo,Hiragino,MS Gothic,Gothic,Yu Gothic,YuGothic,system-ui\">\r\n"
			+ "				<fo:flow flow-name=\"xsl-region-body\">";
	private static String xslFoot = "</fo:flow>\r\n"
			+ "			</fo:page-sequence>\r\n"
			+ "		</fo:root>\r\n"
			+ "	</xsl:template>\r\n"
			+ "</xsl:stylesheet>";
	private static String xslBody1 = "<fo:block text-align=\"center\" margin-bottom=\"10mm\">\r\n"
			+ "						ÉwÉbÉ_Å[ïîï™\r\n"
			+ "					</fo:block>\r\n"
			+ "					<fo:block text-align=\"right\" margin-bottom=\"5mm\">\r\n"
			+ "						Date: <xsl:value-of select=\"date\" />\r\n"
			+ "					</fo:block>\r\n"
			+ "					<fo:block margin-left=\"20mm\">\r\n"
			+ "						Client name: <xsl:value-of select=\"cname\" />\r\n"
			+ "					</fo:block>\r\n"
			+ "					<fo:block margin-left=\"20mm\" margin-bottom=\"5mm\">\r\n"
			+ "						Client age: <xsl:value-of select=\"cage\" />\r\n"
			+ "					</fo:block>\r\n"
			+ "					<fo:block margin-bottom=\"5mm\">\r\n"
			+ "						<xsl:value-of select=\"body\" />\r\n"
			+ "					</fo:block>";
	private static String xslBody2 = "";

	static {
		documents[0].setFormat(xslHead + xslBody1 + xslFoot);
		documents[1].setFormat(xslHead + xslBody1 + "<fo:block>sample2 old format</fo:block>" + xslFoot);
		documents[2].setFormat(xslHead + xslBody1 + "<fo:block>sample2 new format</fo:block>" + xslFoot);
	}
	public static DocumentDTO getDocument(Date start, String name) {
		DocumentDTO ret = null;
		for (int l=0; l < documents.length; l++) {
			DocumentDTO doc = documents[l]; 
			if (doc.getName().equals(name) && (start == null || start.compareTo(doc.getStart()) >= 0)) {
				if (ret == null || ret.getStart().compareTo(doc.getStart()) < 0) {
					ret = doc;
				}
			}
		}
		return ret;
	}
}
