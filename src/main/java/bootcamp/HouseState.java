package bootcamp;

import net.corda.core.contracts.Amount;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

import java.util.Currency;
import java.util.List;

public class HouseState implements ContractState {
    private final Party owner;
    private final int price;

    public HouseState(Party owner, int price) {
        this.owner = owner;
        this.price = price;
    }

    public Party getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public List<AbstractParty> getParticipants(){
        return  null;
    }
}
