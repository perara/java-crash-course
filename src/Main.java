public class Main {



    public static void main(String[] args){
        Chain initialChain = new Chain(0, "", 0, null, null);

        Wallet goofy = new Wallet("Goofy", initialChain);
        Wallet alice = new Wallet("Alice", initialChain);

        goofy.transferCoins(alice, 1);



    }
}
