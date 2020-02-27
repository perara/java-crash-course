package random;

import java.security.*;
import java.util.ArrayList;
import java.util.UUID;



public class Wallet {

    String owner;
    KeyPair keyPair;
    String ID;
    Chain chain;

    public Wallet(String owner, Chain c){
        this.ID = UUID.randomUUID().toString();
        this.owner = owner;
        this.keyPair = this.generateKeyPair();

        this.parseChain(c);

    }


    private void parseChain(Chain c){

        ArrayList<Chain> relevantNodes = new ArrayList<Chain>();

        Chain current = c;
        while(current != null){

            if(current.pubID.equals(this.ID)){
                relevantNodes.add(c);
            }


            current = c.last;
        }


    }

    public void transferCoins(Wallet w, int amount){
        byte[] transaction = (this.ID + ":" + amount + w.ID).getBytes();


        Signature sig = getSignature();
        byte[] signatureBytes = this.sign(sig, transaction);



        //sig.initVerify(keyPair.getPublic());
        //sig.update(data);


        System.out.println(signatureBytes.toString());


    }

    private Signature getSignature(){
        Signature sig = null;

        try {
            sig = Signature.getInstance("SHA1WithRSA");
            sig.initSign(keyPair.getPrivate());

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return sig;
    }

    private byte[] sign(Signature sig, byte[] transaction){
        byte[] signatureBytes = null;
        try {
            sig.update(transaction);
            signatureBytes = sig.sign();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return signatureBytes;
    }

    public KeyPair generateKeyPair(){
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        kpg.initialize(1024);
        return kpg.genKeyPair();
    }




}
