package WarlordEmblem.variables;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import WarlordEmblem.cards.DeathKnight.AbstractDKCard;


public class SecondRealmMagicNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return WarlordEmblem.makeID("SecondRealmMagic");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "Gensokyo:" (or, your ID) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return AbstractDKCard.isSecondRealmMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return AbstractDKCard.SecondRealmMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return AbstractDKCard.BaseSecondRealmMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractDKCard) card).upgradedSecondRealmMagicNumber;
    }
}