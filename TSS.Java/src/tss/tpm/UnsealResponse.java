package tss.tpm;

import tss.*;


// -----------This is an auto-generated file: do not edit

//>>>

/** This command returns the data in a loaded Sealed Data Object. */
public class UnsealResponse extends TpmStructure
{
    /**
     *  unsealed data
     *  Size of outData is limited to be no more than 128 octets.
     */
    public byte[] outData;
    
    public UnsealResponse() {}
    
    @Override
    public void toTpm(OutByteBuf buf) 
    {
        buf.writeSizedByteBuf(outData);
    }

    @Override
    public void initFromTpm(InByteBuf buf)
    {
        int _outDataSize = buf.readShort() & 0xFFFF;
        outData = new byte[_outDataSize];
        buf.readArrayOfInts(outData, 1, _outDataSize);
    }

    @Override
    public byte[] toTpm() 
    {
        OutByteBuf buf = new OutByteBuf();
        toTpm(buf);
        return buf.getBuf();
    }

    public static UnsealResponse fromTpm (byte[] x) 
    {
        UnsealResponse ret = new UnsealResponse();
        InByteBuf buf = new InByteBuf(x);
        ret.initFromTpm(buf);
        if (buf.bytesRemaining()!=0)
            throw new AssertionError("bytes remaining in buffer after object was de-serialized");
        return ret;
    }

    public static UnsealResponse fromTpm (InByteBuf buf) 
    {
        UnsealResponse ret = new UnsealResponse();
        ret.initFromTpm(buf);
        return ret;
    }

    @Override
    public String toString()
    {
        TpmStructurePrinter _p = new TpmStructurePrinter("TPM2_Unseal_RESPONSE");
        toStringInternal(_p, 1);
        _p.endStruct();
        return _p.toString();
    }

    @Override
    public void toStringInternal(TpmStructurePrinter _p, int d)
    {
        _p.add(d, "byte", "outData", outData);
    }
}

//<<<

