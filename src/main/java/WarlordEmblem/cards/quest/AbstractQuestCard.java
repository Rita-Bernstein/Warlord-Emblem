package WarlordEmblem.cards.quest;

import basemod.abstracts.CustomCard;


public abstract class AbstractQuestCard extends CustomCard implements QuestCardMask {

    public AbstractQuestCard(String id, String name,  String img, int cost, String rawDescription,
                             CardType type, CardColor color, CardRarity rarity, CardTarget target) {

        super(id, name,  img, cost, rawDescription, type, color, rarity, target);
    }
    /**
     * Quest Card can NOT upgrade
     */
    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }
}
