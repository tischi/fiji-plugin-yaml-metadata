package de.embl.cba.metadata;

public class JacksonYaml
{
	//	public void createJacksonYamlFile( MetaData metadata, String outputPath )
//	{
//
//		// Create an ObjectMapper mapper for YAML
//		//ObjectMapper mapper = new ObjectMapper(new YAMLFactory().enable( YAMLGenerator.Feature.MINIMIZE_QUOTES ) );
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory()
//				.enable( YAMLGenerator.Feature.MINIMIZE_QUOTES )
//				.enable( YAMLGenerator.Feature.LITERAL_BLOCK_STYLE ));
//
//		try
//		{
//			FileOutputStream fos = new FileOutputStream( outputPath );
//			SequenceWriter sw = mapper.writer().writeValues(fos);
//			sw.write( metadata );
//			fos.close();
//		}
//		catch ( Exception e )
//		{
//			e.printStackTrace();
//		}
//	}
}
