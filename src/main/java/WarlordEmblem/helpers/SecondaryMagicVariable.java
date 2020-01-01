package WarlordEmblem.helpers;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SecondaryMagicVariable extends DynamicVariable
{
    @Override
    public String key()
    {
        return "WarlordEmblemSecondM";
    }

    @Override
    public boolean isModified(AbstractCard card)
    {
            AbstractCard asc = card;
return true;
    }

    @Override
    public int value(AbstractCard card)
    {
        return 0;
    }

    @Override
    public int baseValue(AbstractCard card)
    {
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card)
    {
        return true;
}
}