import java.net.InetAddress;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.reindex.ReindexPlugin;
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ElasticSearchDruidDataSourceFactory;
/**
 * 
 * <pre>
 * es连接基类。
 * </pre>
 * @author 王文辉  wangwenhui@jiaxincloud.com
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public  class EsConnectionFactory {
	
	private final static String ip="172.16.54.74";
//	private final static String ip="59.46.81.19";
//	private static String address="59.46.81.19:9300,59.46.81.17:9300";
//	private final static String ip2="59.46.81.17";
	private final static int port=9300;
 
	static TransportClient client;

/**
 * 
 * @return
 */
	public static TransportClient createEsClient() {
		try{ Settings settings = Settings  
	            .settingsBuilder()  
	            .build();  
			client = TransportClient.builder().settings(settings).addPlugin(DeleteByQueryPlugin.class).addPlugin(ReindexPlugin.class).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));

			if (client != null) {
				return client;
			} else {
				Logger.getLogger(EsConnectionFactory.class).info("connect EsDataServer failed!");
			}
			}catch(Exception e){
				Logger.getLogger(EsConnectionFactory.class).error("connect es excption:"+e.getMessage());
			}
		return null;
	}
	
	/**
	 * 创建es数据库连接
	 * 
	 * @return
	 */
	public static DruidDataSource getEsDataSource(String index) {
		try{
	        Properties properties = new Properties();
	        properties.put("url", "jdbc:elasticsearch://"+ip+":"+port+"/"+index );
	        DruidDataSource dds = (DruidDataSource) ElasticSearchDruidDataSourceFactory.createDataSource(properties);
	        if(dds!=null){
	        	return dds;
	        }
		}catch(Exception e){
			Logger.getLogger(EsConnectionFactory.class).error("connect es datasource excption:"+e.getMessage());
		}
		return null;
	}
	
	
}