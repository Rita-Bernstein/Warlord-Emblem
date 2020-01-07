package WarlordEmblem.variables;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import WarlordEmblem.cards.DeathKnight.AbstractDKCard;


public class RealmMagicNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return WarlordEmblem.makeID("RealmMagic");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "Gensokyo:" (or, your ID) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return AbstractDKCard.isRealmMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return AbstractDKCard.RealmMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return AbstractDKCard.BaseRealmMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractDKCard) card).upgradedRealmMagicNumber;
    }
}