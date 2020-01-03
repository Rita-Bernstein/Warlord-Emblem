package WarlordEmblem.cards.mantle;

import basemod.abstracts.CustomCard;

public abstract class AbstractMantleCard extends CustomCard implements MantleCardMask {

    public AbstractMantleCard(String id, String name,  String img, int cost, String rawDescription,
                              CardType type, CardColor color, CardRarity rarity, CardTarget target) {

        super(id, name,  img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }
}
