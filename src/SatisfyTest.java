
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 * Hello world!
 *
 */
public class SatisfyTest extends EsJdbcDaoSupport {

	public  static void main(String[] args) throws Exception {
		System.out.println("Hello World!");
		SatisfyTest st =new SatisfyTest();
		st.createSDRIndex();
//		st.testDelete();
//		st.testJDBC();
		st.testDelete();
		st.updateSDR();
	}

	public  void createSDRIndex() throws Exception {
		XContentBuilder mapping = XContentFactory.jsonBuilder()
				.startObject()
				.startObject("settings")
				.field("number_of_shards", 5)// ���÷�Ƭ����
				.field("number_of_replicas", 0)// ���ø�������
				.endObject()
					.startObject("mappings")
						.startObject("ST_AGENT_QS_DETAIL_SDR")// type����
								.startObject("properties") // �����������ĵ������ԡ�
							
							.startObject("QS_ENTERPRISE_ID").field("type", "string").field("index", "not_analyzed").endObject()
							.startObject("QS_SESSION_ID").field("type", "string").field("index", "not_analyzed").endObject()
							.startObject("QS_TIME").field("type", "date").field("format", "yyyyMMddHHmmss").endObject()
							.startObject("QS_SESSION_TIME").field("type", "date").field("format", "yyyyMMddHHmmss").endObject()
							.startObject("QS_BIZ_TYPE").field("type", "integer").field("index", "not_analyzed").endObject()
							.startObject("QS_SESSION_AGENT_ID").field("type", "string").field("index", "not_analyzed").endObject()
							.startObject("QS_SESSION_WORKGROUP_ID").field("type", "string").field("index", "not_analyzed").endObject()
							.startObject("QS_QS_AGENT_ID").field("type", "string").field("index", "not_analyzed").endObject()
							.startObject("QS_SCORE_TYPE_ID").field("type", "string").field("index", "not_analyzed").endObject()
							.startObject("QS_LEVEL_ID").field("type", "string").field("index", "not_analyzed").endObject()
							.startObject("QS_SCORE").field("type", "integer").field("index", "not_analyzed").endObject()
							.startObject("QS_ID").field("type", "string").field("index", "not_analyzed").endObject()
								.endObject()
						.endObject()
					.endObject()
					.endObject();

		TransportClient client =EsConnectionFactory.createEsClient();
		CreateIndexRequestBuilder cirb = client.admin().indices().prepareCreate("st_agent_qs_detail_sdr")// index����
				.setSource(mapping);

		CreateIndexResponse response = cirb.execute().actionGet();
		if (response.isAcknowledged()) {
			System.out.println("Index created.");
		} else {
			System.err.println("Index creation failed.");
		}
		client.close();
	}

	public  void putSDR() {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("SS_TIME", "20160829152817");
		json.put("SS_ENTERPRISE_ID", "ctdiznh1b3rnbg");
		json.put("SS_SESSION_ID", "fhnxzt13015-hui");
		json.put("SS_AGENT_ID", "ctdiznh1b3raget#mcs_cd5752786745fe80b3f7ced4de648d0a");
		json.put("SS_SATISFY_SCORE", 1);
		json.put("SS_APP_NAME", "aa588");
		json.put("SS_VISITOR_ID", "ctdiznh1b3rhui#aa588_web3178352725333775");
		json.put("SS_WORKGROUP_ID", "ctdiznh1b3shzxhui#service5752786745fe80b3f7ced4de648d0a");
		insert("st_agent_satisfy_sdr", "ST_AGENT_SATISFY_SDR", json);
		System.out.println("insert success.");
	}
	
	public  void  updateSDR() {
//		Map<String, Object> json = new HashMap<String, Object>();
//		json.put("SS_TIME", "20160829152817");
//		json.put("SS_ENTERPRISE_ID", "ctdiznh1b3rnbg");
//		json.put("SS_SESSION_ID", "fhnxzt13015");
//		json.put("SS_AGENT_ID", "ctdiznh1b3raget#mcs_cd5752786745fe80b3f7ced4de648d0a");
//		json.put("SS_SATISFY_SCORE", 1);
//		json.put("SS_APP_NAME", "aa588");
//		json.put("SS_VISITOR_ID", "ctdiznh1b3rhui#aa588_web3178352725333775");
//		json.put("SS_WORKGROUP_ID", "ctdiznh1b3shzxhui#service5752786745fe80b3f7ced4de648d0a");
////		insert("st_agent_satisfy_sdr", "ST_AGENT_SATISFY_SDR", json);
		SatisfySDREntity s=new SatisfySDREntity();
//		s.setSsTime("20160829152817");
//		s.setSsEnterpriseId("ctdiznh1b3rnbg");
//		s.setSsAgentId("ctdiznh1b3raget#mcs_cd5752786745fe80b3f7ced4de648d0a");
//		s.setSsAppName("aa588");
//		s.setSsVisitorId("ctdiznh1b3rhui#aa588_web3178352725333775");
//		s.setChannelNo("2001");
//		s.setWorkgroupId("wenhuicshizu");
//		s.setSsSatisfyScore(90);
//		s.setSsSessionId("fhnxzt13015-wenhui");
		update("st_session_tag", "ST_SESSION_TAG", s);
//		System.out.println("update success.");
	}
    public  void testJDBC() throws Exception {
    	String sql="SELECT COUNT(*) AS count, VS_CHANNEL_NO FROM st_agent_visitor_sdr WHERE  VS_TIME BETWEEN   '20161031000000'  AND  '20161031235959'   AND VS_ENTERPRISE_ID= 'nnvxytfuync0nq' "
    			+ " AND VS_VISITOR_CHANNEL = '1'   GROUP BY VS_CHANNEL_NO";
    	ResultSet rs=query(sql);
  try{
	  while (rs.next()) {
    	  System.out.println( rs.getString("VS_CHANNEL_NO") );
          System.out.println( rs.getDouble("count") );
     
    }
  }catch(SQLException e ){
		 String error=e.getCause().toString();
		 if(error.indexOf("IndexOutOfBoundsException")>0){
				System.out.println("data is null");
		 }
  
  }
      
    }
    public  void testDelete() throws Exception {
    	System.out.println(delete ("st_agent_qs_detail_sdr","ST_AGENT_QS_DETAIL_SDR","ymn0bjbnzxk0dw"));
    }
}
