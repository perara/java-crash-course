import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;

public class Bank extends Wallet {

    private Transaction chain;

    public Bank(String name, ArrayList<PublicKey> allPublicKeys) {
        super(name, allPublicKeys);

        chain = new Transaction(this.keys.getPublic(), 100, this.keys.getPublic());
        byte[] signedAgreement = this.signTransaction(chain);


    }

    public void transfer(Wallet receiver, double amount) {

        
    }


}
