package cbde.labs.hbase_mapreduce.writer;

public class MyHBaseWriter_VerticalPartitioning extends MyHBaseWriter {

	protected String toFamily(String attribute) {
		String family;
		if(attribute.equals("type") || attribute.equals("region") || attribute.equals("flav"))
			family = "fam_1";
		else
			family = "fam_2";
		return family;
	}
		
}
