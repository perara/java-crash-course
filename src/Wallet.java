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
        // Name of the wallet holder
        this.name = name;

        // Variable to keep track of the private and public  key of the wallet
        this.keys = this.generateKeys();

        // The global list of public keys (other wallets)
        this.allPublicKeys = allPublicKeys;

        // Add public key of this wallet to the global list of public keys
        this.allPublicKeys.add(this.keys.getPublic());
    }


    public byte[] signTransaction(Transaction tx){

        // Create signature
        Signature sign = this.createSignature();

        // Add transaction data and digitally sign the transaction

        return this._signTransaction(sign, tx);

    }


    public void verifyTransaction(byte[] txBytes, byte[] signedTx) {
        /*
        This function verifies a transaction by checking if a signature is valid<
         */

        // Iterate through all public keys
        for (PublicKey pubKey: this.allPublicKeys) {

            // Create a new instance of the Signature class
            Signature sign = this.createSignature();

            // Initialize a boolean that defaults to false
            boolean isOK = false;


            try {
                // Initialize the signature with the public key
                sign.initVerify(pubKey);

                // Add the transaction bytes (the message) to the signature
                sign.update(txBytes);

                // Verify that the message was signed by the private key corresponding to the public key
                isOK = sign.verify(signedTx);


            } catch (InvalidKeyException | SignatureException e) {
                // Something wrong happened
                e.printStackTrace();
            }

            if(isOK) {
                System.out.println("The transaction was verified.");

                // Deserialize bytes to the Transaction class
                Transaction tx = this.deserialize(txBytes);

                // Print out some stats
                System.out.println(tx.id + " - " + tx.amount);


            }

        }





    }

    // Internal function, signs the transaction hash
    private byte[] _signTransaction(Signature sign, Transaction tx){
        // Initialize empty signature buffer
        byte[] signed = null;
        try {

            // Serialize the transaction to a byte array then update the signautre
            sign.update(this.serialize(tx));

            // Sign the message (the transaction) with the private key
            signed = sign.sign();

        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return signed;
    }

    public Signature createSignature(){
        // Initialize empty signature variable
        Signature signature = null;
        try {
            // Create signature instance
            signature = Signature.getInstance("SHA1withRSA");

            // Initialize signature with the private key
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
