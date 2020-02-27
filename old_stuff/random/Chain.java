package random;

public class Chain {
    int iteration;
    String pubID;
    int amount;
    byte[] signature;
    Chain last = null;

    public Chain(int iteration, String pubID, int amount, byte[] signature, Chain last){
        this.iteration = iteration;
        this.pubID = pubID;
        this.amount = amount;
        this.signature = signature;
        this.last = last;
    }
}
