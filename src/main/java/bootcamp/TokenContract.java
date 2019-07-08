package bootcamp;

import jdk.nashorn.internal.parser.Token;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;
import org.jetbrains.annotations.NotNull;

/* Our contract, governing how our state will evolve over time.
 * See src/main/java/examples/ArtContract.java for an example. */
public class TokenContract implements Contract {
    public static String ID = "bootcamp.TokenContract";

    @Override
    public void verify(@NotNull LedgerTransaction tx) throws IllegalArgumentException {
        if(tx.getInputStates().size() != 0){
            throw  new IllegalArgumentException("Tx should have 0 input states");
        }

        if(tx.getOutputStates().size() != 1){
            throw new IllegalArgumentException("Tx should have exactly 1 output states");
        }

        if(tx.getCommands().size() != 1){
            throw new IllegalArgumentException("Tx should have exactly 1 commnand");
        }

        if(!(tx.getOutput(0) instanceof TokenState)){
            throw new IllegalArgumentException("Tx output should be a token");
        }

        if(!(tx.getCommand(0).getValue() instanceof Commands.Issue)){
            throw new IllegalArgumentException("Tx should be an issue command");
        }

        if(!(((TokenState) tx.getOutput(0)).getAmount()> 0)){
            throw new IllegalArgumentException("Amount should be greater than 0");
        }

        if(!(tx.getCommand(0).getSigners().contains(((TokenState) tx.getOutput(0)).getIssuer().getOwningKey()))){
            throw new IllegalArgumentException("Issuer is required");
        }

    }


    public interface Commands extends CommandData {
        class Issue implements Commands {

        }
        class Move implements Commands{

        }
    }
}