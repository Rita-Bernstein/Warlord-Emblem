package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CharacterSelectScreenPatches;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class EvilRealm extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("EvilRealm");;
    private static Texture texture = new Texture(WarlordEmblem.assetPath("/img/relics/evil_realm.png")) ;

    public EvilRealm() {
        super(ID, texture, AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
    }

    public void onEquip(){
        if (!(CharacterSelectScreenPatches.TalentCount == 5||
                CharacterSelectScreenPatches.TalentCount == 5+3||
                CharacterSelectScreenPatches.TalentCount == 1+5||
                CharacterSelectScreenPatches.TalentCount == 1+3+5)){
            CharacterSelectScreenPatches.TalentCount += 5;
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new EvilRealm();
    }
}
