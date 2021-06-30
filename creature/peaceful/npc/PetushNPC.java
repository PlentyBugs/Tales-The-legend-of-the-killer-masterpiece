package creature.peaceful.npc;

import abilities.passive.CriticalStrike;
import abilities.passive.Evasion;
import abilities.passive.TwoOneHandedWeapon;
import abilities.passive.professions.Alchemist;
import abilities.passive.professions.Steal;
import conversation.QuestDialogConversation;
import creature.Player;
import creature.aggressive.Goblin;
import creature.peaceful.Dealer;
import item.Grade;
import item.Item;
import item.Material;
import item.Rarity;
import item.alchemy.potion.HealPotion;
import item.alchemy.potion.PoisonPotion;
import item.armor.Helmet;
import item.enchant.EnchantStone;
import item.quest.KingGoblinRing;
import item.weapon.WeaponType;
import item.weapon.sword.Sword;
import quest.CollectItemQuest;
import quest.KillQuest;
import support.CreatureProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class PetushNPC extends Dealer {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Dealer.propertyList);
        propertyList.add(CreatureProperty.PETUSH);
    }

    public PetushNPC(Player player) {
        super(1,1,"Петуш", 57, 59000);
        setStarterPhrase("Добрый день, путник.")
                .addItemToInventory(
                        new HealPotion(),
                        new HealPotion(),
                        new HealPotion(),
                        new PoisonPotion(),
                        new PoisonPotion(),
                        new PoisonPotion(),
                        new PoisonPotion(),
                        new PoisonPotion(),
                        new EnchantStone(),
                        new EnchantStone(),
                        new EnchantStone()
                );
        addConversationShop(1);
        addConversationTrain(2, "Тренировка",
                new Object[] {new TwoOneHandedWeapon(), 188000, 1},
                new Object[] {new CriticalStrike(), 45000, 1},
                new Object[] {new Evasion(), 38000, 1},
                new Object[] {new Steal(), 99000, 1},
                new Object[] {new Alchemist(), 235000, 1}
        ).getConversationWindow().setPlayer(player);

        QuestDialogConversation questDialogConversationDealer = new QuestDialogConversation();
        KillQuest questDealer = new KillQuest();
        questDealer.setExpReward(15000)
                .setGoldReward(48000)
                .setTitle("Зеленая опасность!");
        questDealer.setEnemyCountToKill(16)
                .setEnemyToKill(new Goblin())
                .setEmployerName(getName())
                .setEmployer(this)
                .setConversationEmployer(questDialogConversationDealer);

        questDialogConversationDealer.setTitle(questDealer.getTitle());
        questDialogConversationDealer.setText("Иди убей 16 гоблинов");
        questDialogConversationDealer.setPlayerText("У тебя есть для меня задание?");
        questDialogConversationDealer.setQuest(questDealer);
        QuestDialogConversation questDialogConversationDealer2 = new QuestDialogConversation();
        CollectItemQuest questDealer2 = new CollectItemQuest();
        questDealer2
                .setExpReward(78120)
                .setGoldReward(253000);
        questDealer2.setItem(new KingGoblinRing());
        questDealer2.setPlayer(player);
        questDealer2
                .setTitle("Король гоблинов")
                .setEmployerName(getName())
                .setEmployer(this);
        questDealer2.setItemCount(1);
        questDealer2.setConversationEmployer(questDialogConversationDealer2);
        questDialogConversationDealer2.setTitle(questDealer2.getTitle());
        questDialogConversationDealer2.setText("Да, тут где-то находится Король гоблинов, крайне сильная тварь, мне нужно его кольцо, говорят, что оно стоит немалых денег!");
        questDialogConversationDealer2.setPlayerText("У тебя есть еще что-нибудь для меня?");
        questDialogConversationDealer2.setQuest(questDealer2);
        questDialogConversationDealer2.setIsVisible(false);
        questDialogConversationDealer.addConversationBranch(questDialogConversationDealer2, 1);
        addConversationDialog(3, questDialogConversationDealer);

        addItemToInventory(
                new Sword(Material.ELVENMYTHRIL, Rarity.LEGENDARY, Grade.ARTIFACT, 3, WeaponType.TWO_HANDED),
                new Helmet(Material.ELVENMYTHRIL, Rarity.MYSTICAL, Grade.ARTIFACT, 1)
        );

        for (Item item : getInventory()) {
            item.countProperty();
        }
    }
}
