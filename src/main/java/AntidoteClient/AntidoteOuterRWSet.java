package main.java.AntidoteClient;

import java.util.List;

import com.google.protobuf.ByteString;

import interfaces.SetCRDT;

/**
 * The Class AntidoteOuterRWSet.
 */
public final class AntidoteOuterRWSet extends AntidoteOuterSet implements SetCRDT{
	
	/** The low level set. */
	private final RWSetRef lowLevelSet;
	
	/**
	 * Instantiates a new antidote RW set.
	 *
	 * @param name the name
	 * @param bucket the bucket
	 * @param valueList the value list
	 * @param antidoteClient the antidote client
	 */
	public AntidoteOuterRWSet(String name, String bucket, List<ByteString> valueList, AntidoteClient antidoteClient){
		super(name, bucket, valueList, antidoteClient, AntidoteType.RWSetType);
		lowLevelSet = new RWSetRef(name, bucket, antidoteClient);
	}
	
	/**
	 * Gets the most recent state from the database.
	 */
	public void readDatabase(AntidoteTransaction antidoteTransaction){
		setValues(lowLevelSet.readValueListBS(antidoteTransaction));
	}
}
