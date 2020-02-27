import java.io.*;
import java.security.*;
import java.util.ArrayList;

public class Wallet {



    String ID;
    String name;
    KeyPair keys;
    double coins;
    ArrayList<PublicKey> allPublicKeys;

    public Wallet(String name, ArrayList<PublicKey> allPublicKeys){
        this.name = name;
        this.keys = this.generateKeys();
        this.allPublicKeys = allPublicKeys;

        this.allPublicKeys.add(this.keys.getPublic());
    }

    /*
    Cheat function!
     */
    public void giveCoins(double amount){
        this.coins += amount;
    }

    public byte[] signTransaction(Transaction tx){

        // Create signature
        Signature sign = this.createSignature();

        // Add transaction data and digitally sign the transaction

        //byte[] signBytes = sign.

        return this._signTransaction(sign, tx);

    }

    public void verifyTransaction(byte[] txBytes, byte[] signedTx) {

        for (PublicKey pubKey: this.allPublicKeys) {

            Signature sign = this.createSignature();
            boolean isOK = false;
            // Add the signed TX to a new signature instance
            try {
                sign.initVerify(pubKey);
                sign.update(txBytes);
                isOK = sign.verify(signedTx);
            } catch (InvalidKeyException | SignatureException e) {
                e.printStackTrace();
            }

            if(isOK) {
                System.out.println("The transaction was verified.");

                Transaction tx = this.deserialize(txBytes);

                System.out.println(tx.id + " - " + tx.amount);


            }

        }





    }

    // Internal function, signs the transaction hash
    private byte[] _signTransaction(Signature sign ,Transaction tx){
        byte[] signed = null;
        try {
            sign.update(this.serialize(tx));
            signed = sign.sign();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return signed;
    }

    public Signature createSignature(){
        Signature signature = null;
        try {
            signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(keys.getPrivate());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return signature;
    }

    public KeyPair generateKeys(){
        // Create a KeyPairGenerator for creating private and public keys to the wallet
        KeyPairGenerator keyGen = null;
        try {
            // Here, we attempt to get the RSA2 priv/pub key generator
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // We generate the keypair
        KeyPair newKeyPair = keyGen.genKeyPair();

        // Return the keypair
        return newKeyPair;
    }


    public byte[] serialize(Transaction tx){
        byte[] yourBytes = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(bos);
            out.writeObject(tx);
            out.flush();
            yourBytes = bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return yourBytes;
    }

    public Transaction deserialize(byte[] txBytes){
        Transaction o = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(txBytes);
        try (ObjectInput in = new ObjectInputStream(bis)) {
            o = (Transaction)in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;

    }


}
