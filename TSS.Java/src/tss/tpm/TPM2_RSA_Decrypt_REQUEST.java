package tss.tpm;

import tss.*;


// -----------This is an auto-generated file: do not edit

//>>>

/**
 *  This command performs RSA decryption using the indicated padding scheme according
 *  to IETF RFC 8017 ((PKCS#1).
 */
public class TPM2_RSA_Decrypt_REQUEST extends TpmStructure
{
    /**
     *  RSA key to use for decryption
     *  Auth Index: 1
     *  Auth Role: USER
     */
    public TPM_HANDLE keyHandle;
    
    /**
     *  cipher text to be decrypted
     *  NOTE An encrypted RSA data block is the size of the public modulus.
     */
    public byte[] cipherText;
    
    /** the padding scheme to use if scheme associated with keyHandle is TPM_ALG_NULL */
    public TPMU_ASYM_SCHEME inScheme;
    
    /** label whose association with the message is to be verified */
    public byte[] label;
    
    public TPM2_RSA_Decrypt_REQUEST() { keyHandle = new TPM_HANDLE(); }
    
    /**
     *  @param _keyHandle RSA key to use for decryption
     *         Auth Index: 1
     *         Auth Role: USER
     *  @param _cipherText cipher text to be decrypted
     *         NOTE An encrypted RSA data block is the size of the public modulus.
     *  @param _inScheme the padding scheme to use if scheme associated with keyHandle is TPM_ALG_NULL
     *         (One of [TPMS_KEY_SCHEME_ECDH, TPMS_KEY_SCHEME_ECMQV, TPMS_SIG_SCHEME_RSASSA,
     *         TPMS_SIG_SCHEME_RSAPSS, TPMS_SIG_SCHEME_ECDSA, TPMS_SIG_SCHEME_ECDAA, TPMS_SIG_SCHEME_SM2,
     *         TPMS_SIG_SCHEME_ECSCHNORR, TPMS_ENC_SCHEME_RSAES, TPMS_ENC_SCHEME_OAEP,
     *         TPMS_SCHEME_HASH, TPMS_NULL_ASYM_SCHEME])
     *  @param _label label whose association with the message is to be verified
     */
    public TPM2_RSA_Decrypt_REQUEST(TPM_HANDLE _keyHandle, byte[] _cipherText, TPMU_ASYM_SCHEME _inScheme, byte[] _label)
    {
        keyHandle = _keyHandle;
        cipherText = _cipherText;
        inScheme = _inScheme;
        label = _label;
    }

    public int GetUnionSelector_inScheme()
    {
        if (inScheme instanceof TPMS_KEY_SCHEME_ECDH) { return 0x0019; }
        if (inScheme instanceof TPMS_KEY_SCHEME_ECMQV) { return 0x001D; }
        if (inScheme instanceof TPMS_SIG_SCHEME_RSASSA) { return 0x0014; }
        if (inScheme instanceof TPMS_SIG_SCHEME_RSAPSS) { return 0x0016; }
        if (inScheme instanceof TPMS_SIG_SCHEME_ECDSA) { return 0x0018; }
        if (inScheme instanceof TPMS_SIG_SCHEME_ECDAA) { return 0x001A; }
        if (inScheme instanceof TPMS_SIG_SCHEME_SM2) { return 0x001B; }
        if (inScheme instanceof TPMS_SIG_SCHEME_ECSCHNORR) { return 0x001C; }
        if (inScheme instanceof TPMS_ENC_SCHEME_RSAES) { return 0x0015; }
        if (inScheme instanceof TPMS_ENC_SCHEME_OAEP) { return 0x0017; }
        if (inScheme instanceof TPMS_SCHEME_HASH) { return 0x7FFF; }
        if (inScheme instanceof TPMS_NULL_ASYM_SCHEME) { return 0x0010; }
        throw new RuntimeException("Unrecognized type");
    }

    @Override
    public void toTpm(OutByteBuf buf) 
    {
        keyHandle.toTpm(buf);
        buf.writeSizedByteBuf(cipherText);
        buf.writeShort(GetUnionSelector_inScheme());
        ((TpmMarshaller)inScheme).toTpm(buf);
        buf.writeSizedByteBuf(label);
    }

    @Override
    public void initFromTpm(InByteBuf buf)
    {
        keyHandle = TPM_HANDLE.fromTpm(buf);
        int _cipherTextSize = buf.readShort() & 0xFFFF;
        cipherText = new byte[_cipherTextSize];
        buf.readArrayOfInts(cipherText, 1, _cipherTextSize);
        int _inSchemeScheme = buf.readShort() & 0xFFFF;
        inScheme = null;
        if (_inSchemeScheme == TPM_ALG_ID.ECDH.toInt()) { inScheme = new TPMS_KEY_SCHEME_ECDH(); }
        else if (_inSchemeScheme == TPM_ALG_ID.ECMQV.toInt()) { inScheme = new TPMS_KEY_SCHEME_ECMQV(); }
        else if (_inSchemeScheme == TPM_ALG_ID.RSASSA.toInt()) { inScheme = new TPMS_SIG_SCHEME_RSASSA(); }
        else if (_inSchemeScheme == TPM_ALG_ID.RSAPSS.toInt()) { inScheme = new TPMS_SIG_SCHEME_RSAPSS(); }
        else if (_inSchemeScheme == TPM_ALG_ID.ECDSA.toInt()) { inScheme = new TPMS_SIG_SCHEME_ECDSA(); }
        else if (_inSchemeScheme == TPM_ALG_ID.ECDAA.toInt()) { inScheme = new TPMS_SIG_SCHEME_ECDAA(); }
        // code generator workaround BUGBUG >> (probChild)else if (_inSchemeScheme == TPM_ALG_ID.SM2.toInt()) { inScheme = new TPMS_SIG_SCHEME_SM2(); }
        // code generator workaround BUGBUG >> (probChild)else if (_inSchemeScheme == TPM_ALG_ID.ECSCHNORR.toInt()) { inScheme = new TPMS_SIG_SCHEME_ECSCHNORR(); }
        else if (_inSchemeScheme == TPM_ALG_ID.RSAES.toInt()) { inScheme = new TPMS_ENC_SCHEME_RSAES(); }
        else if (_inSchemeScheme == TPM_ALG_ID.OAEP.toInt()) { inScheme = new TPMS_ENC_SCHEME_OAEP(); }
        else if (_inSchemeScheme == TPM_ALG_ID.ANY.toInt()) { inScheme = new TPMS_SCHEME_HASH(); }
        else if (_inSchemeScheme == TPM_ALG_ID.NULL.toInt()) { inScheme = new TPMS_NULL_ASYM_SCHEME(); }
        if (inScheme == null) throw new RuntimeException("Unexpected type selector " + TPM_ALG_ID.fromInt(_inSchemeScheme).name());
        inScheme.initFromTpm(buf);
        int _labelSize = buf.readShort() & 0xFFFF;
        label = new byte[_labelSize];
        buf.readArrayOfInts(label, 1, _labelSize);
    }

    @Override
    public byte[] toTpm() 
    {
        OutByteBuf buf = new OutByteBuf();
        toTpm(buf);
        return buf.getBuf();
    }

    public static TPM2_RSA_Decrypt_REQUEST fromTpm (byte[] x) 
    {
        TPM2_RSA_Decrypt_REQUEST ret = new TPM2_RSA_Decrypt_REQUEST();
        InByteBuf buf = new InByteBuf(x);
        ret.initFromTpm(buf);
        if (buf.bytesRemaining()!=0)
            throw new AssertionError("bytes remaining in buffer after object was de-serialized");
        return ret;
    }

    public static TPM2_RSA_Decrypt_REQUEST fromTpm (InByteBuf buf) 
    {
        TPM2_RSA_Decrypt_REQUEST ret = new TPM2_RSA_Decrypt_REQUEST();
        ret.initFromTpm(buf);
        return ret;
    }

    @Override
    public String toString()
    {
        TpmStructurePrinter _p = new TpmStructurePrinter("TPM2_RSA_Decrypt_REQUEST");
        toStringInternal(_p, 1);
        _p.endStruct();
        return _p.toString();
    }

    @Override
    public void toStringInternal(TpmStructurePrinter _p, int d)
    {
        _p.add(d, "TPM_HANDLE", "keyHandle", keyHandle);
        _p.add(d, "byte", "cipherText", cipherText);
        _p.add(d, "TPMU_ASYM_SCHEME", "inScheme", inScheme);
        _p.add(d, "byte", "label", label);
    }
}

//<<<

