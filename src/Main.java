import java.security.PublicKey;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args){

        ArrayList<PublicKey> allPublicKeys = new ArrayList<PublicKey>();

        Wallet alice = new Wallet("Alice", allPublicKeys);
        Wallet bob = new Wallet("Bob", allPublicKeys);

        Bank bank = new Bank("Bank", allPublicKeys);
        bank.transfer(alice, 5);
        bank.transfer(bob, 5);


        // Create a transaction for sending money from alice to bob
        Transaction first_tx = new Transaction(alice.keys.getPublic(), 5.0, bob.keys.getPublic());
        byte[] signedAgreement = alice.signTransaction(first_tx);

        bob.verifyTransaction(alice.serialize(first_tx), signedAgreement);






    }


}
