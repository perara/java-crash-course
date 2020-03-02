import java.security.PublicKey;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args){
        // Create a array (list) where we save all PublicKeys
        ArrayList<PublicKey> allPublicKeys = new ArrayList<PublicKey>();

        // Create a wallet that belongs to alice
        Wallet alice = new Wallet("Alice", allPublicKeys);

        // Create a wallet that belongs to bob
        Wallet bob = new Wallet("Bob", allPublicKeys);

        // Create a bank (central unit) wallet
        Bank bank = new Bank("Bank", allPublicKeys);

        // Transfer 5 coins to alice
        bank.transfer(alice, 5);

        // Transfer 5 coins to bob
        bank.transfer(bob, 5);


        // Create a transaction for sending money from alice to bob
        // TODO - This part is not doen.
        Transaction first_tx = new Transaction(alice.keys.getPublic(), 5.0, bob.keys.getPublic());
        byte[] signedAgreement = alice.signTransaction(first_tx);

        bob.verifyTransaction(alice.serialize(first_tx), signedAgreement);






    }


}
