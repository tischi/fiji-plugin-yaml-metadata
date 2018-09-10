package de.embl.cba.metadata;

import loci.common.services.ServiceFactory;
import loci.formats.IFormatReader;
import loci.formats.ImageReader;
import loci.formats.meta.IMetadata;
import loci.formats.services.OMEXMLService;
import ome.units.UNITS;
import ome.units.quantity.Length;
import ome.units.quantity.Time;

import java.io.File;
import java.util.*;

import static de.embl.cba.metadata.Utils.log;

public class MetadataCreator
{
	public static final String IMAGE_DIMENSIONS = "Image dimensions";
	public static final String ENTITY = "Entity";
	public static final String LABEL = "Label";
	public static final String TOTAL_DATA_SIZE = "Total data size";
	public static final String UNKNOWN = "NA";
	public static final String TIME_POINTS = "Time points";
	public static final String SPECIES = "Species";
	public static final String GENES = "Genes";
	public static final String POSITIONS = "Positions";

	IFormatReader reader;
	IMetadata meta;
	int series;
	Map< String, Object > map;
	Metadata metadata;
	String filePath;

	public MetadataCreator( String filePath )
	{
		this.filePath = filePath;
		this.map = new LinkedHashMap<>(  );
		initialiseBioformats();
		populateMetadataMap();
		metadata = new Metadata();
		metadata.metadata = map;
	}

	private void initialiseBioformats( )
	{
		try
		{
			// create OME-XML metadata store
			ServiceFactory factory = new ServiceFactory();
			OMEXMLService service = factory.getInstance( OMEXMLService.class );
			meta = service.createOMEXMLMetadata();

			// create format reader
			reader = new ImageReader();
			reader.setMetadataStore( meta );

			// initialize filePath
			log( "Analyzing " + filePath );
			reader.setId( filePath );

			int seriesCount = reader.getSeriesCount();
			series = reader.getSeries();
			log( "\tImage series = " + series + " of " + seriesCount );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	private void populateMetadataMap()
	{
		addTotalDataSize();
		addDimensions();
		addPhysicalDimensions();
		addChannels();
		addTimePoints();
		addPositions();
		addSpecies();
		map.put( "Imaging method", UNKNOWN );
		map.put( "Developmental stage", UNKNOWN );
		map.put( "Cell line", UNKNOWN );

	}

	private void addPhysicalDimensions()
	{

		Length physicalSizeX = meta.getPixelsPhysicalSizeX(series);
		Length physicalSizeY = meta.getPixelsPhysicalSizeY(series);
		Length physicalSizeZ = meta.getPixelsPhysicalSizeZ(series);
		Time timeIncrement = meta.getPixelsTimeIncrement(series);

		final LinkedHashMap< String, Object > physicalDimensions = new LinkedHashMap<>();
		physicalDimensions.put( "X", physicalSizeX.value() + " " + physicalSizeX.unit().getSymbol() );
		physicalDimensions.put( "Y", physicalSizeY.value() + " " + physicalSizeY.unit().getSymbol() );
		physicalDimensions.put( "Z", physicalSizeZ.value() + " " + physicalSizeZ.unit().getSymbol() );
		physicalDimensions.put( "T", timeIncrement.value( UNITS.SECOND ).doubleValue() + " seconds" );
		map.put( "Physical dimensions", physicalDimensions );
	}

	private void addSpecies()
	{
		final LinkedHashMap< String, Object > species = new LinkedHashMap<>();
		species.put( "Name", UNKNOWN );
		species.put( "Taxon", UNKNOWN );
		map.put( SPECIES, species );
	}


	public void addGenes()
	{
		final LinkedHashMap< String, Object > genes = new LinkedHashMap<>();
		genes.put( "Symbols", UNKNOWN );
		genes.put( "Identifiers", UNKNOWN );
		map.put( GENES, genes );
	}


	public void addPositions()
	{
		int numPositions = reader.getSeriesCount();
		List< String > positions = new LinkedList<>(  );

		for ( int positionId = 0; positionId < numPositions; ++positionId )
		{
			positions.add( "Pos_" + String.format( "%05d",  positionId + 1 ) );
		}

		map.put( POSITIONS, positions );
	}

	public void addTimePoints()
	{
		int numTimepoints = reader.getSizeT();
		ArrayList< String > timepoints = new ArrayList<>(  );

		for ( int positionId = 0; positionId < numTimepoints; ++positionId )
		{
			timepoints.add( "Time_" + String.format( "%05d",  positionId + 1 ) );
		}

		map.put( TIME_POINTS, timepoints );

	}

	public void addTotalDataSize()
	{
		final File file = new File( filePath );
		final long fileSizeInBytes = file.length();
		double fileSizeInMB = fileSizeInBytes / ( 1024.0 * 1024.0 );
		map.put( TOTAL_DATA_SIZE, "" + Math.round( fileSizeInMB ) + " Mb" );
	}

	public Metadata getMetadata()
	{
		return metadata;
	}

	public void addDimensions()
	{
		int sizeX = reader.getSizeX();
		int sizeY = reader.getSizeY();
		int sizeZ = reader.getSizeZ();
		int sizeC = reader.getSizeC();
		int sizeT = reader.getSizeT();

		map.put( IMAGE_DIMENSIONS, sizeX + "x" + sizeY + "x" + sizeZ + "x" + sizeC + "x" + sizeT );
	}

	public void addChannels()
	{
		int numChannels = reader.getSizeC();

		Map< String, Object > channels = new LinkedHashMap<>( );

		for ( int channelId = 0; channelId < numChannels; ++channelId )
		{
			Map< String, Object > channelProperties = new LinkedHashMap<>( );
			channelProperties.put( ENTITY, UNKNOWN );
			channelProperties.put( LABEL, UNKNOWN );

			channels.put( "Channel " + channelId, channelProperties );
		}

		map.put( "Channels", channels );
	}

}
