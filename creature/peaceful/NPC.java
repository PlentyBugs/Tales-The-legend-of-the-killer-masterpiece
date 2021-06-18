package creature.peaceful;

import abilities.active.DamageUp;
import abilities.active.DecreaseDamage;
import abilities.active.Rage;
import abilities.auras.Vision;
import abilities.passive.CriticalStrike;
import abilities.passive.Evasion;
import abilities.passive.professions.Alchemist;
import abilities.passive.professions.BlackSmith;
import abilities.passive.professions.Steal;
import abilities.passive.TwoOneHandedWeapon;
import conversation.DialogConversation;
import conversation.QuestDialogConversation;
import creature.aggressive.Bandit;
import creature.aggressive.boss.DeadGuardian;
import creature.aggressive.boss.Frank;
import creature.aggressive.boss.HigherGhost;
import creature.aggressive.Goblin;
import creature.Player;
import item.alchemy.potion.HealPotion;
import item.alchemy.potion.PoisonPotion;
import item.armor.Helmet;
import item.blacksmith.BluePrint;
import item.blacksmith.ItemCraftType;
import item.blacksmith.resource.Adamantine;
import item.blacksmith.resource.Mythril;
import item.enchant.EnchantStone;
import item.Grade;
import item.Item;
import item.Material;
import item.quest.KingGoblinRing;
import item.Rarity;
import item.tool.Pickaxe;
import item.weapon.sword.Sword;
import item.weapon.WeaponType;
import quest.*;

import java.io.Serializable;

public class NPC {
    public static Inhabitant inhabitantDanil;
    public static Dealer dealerShutep;
    public static Dealer dealerPetush;
    public static BlackSmithCraftMan blacksmithDroghan;

    public NPC(Player player){
        dealerPetush = new Dealer(1,1,"Петуш", 57, 59000);
        dealerPetush.setStarterPhrase("Добрый день, путник.")
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
        dealerPetush.addConversationShop(1);
        dealerPetush.addConversationTrain(2, "Тренировка",
                new Object[] {new TwoOneHandedWeapon(), 188000, 1},
                new Object[] {new CriticalStrike(), 45000, 1},
                new Object[] {new Evasion(), 38000, 1},
                new Object[] {new Steal(), 99000, 1},
                new Object[] {new Alchemist(), 235000, 1}
        )
                .getConversationWindow().setPlayer(player);

        QuestDialogConversation questDialogConversationDealer = new QuestDialogConversation();
        KillQuest questDealer = new KillQuest();
        questDealer.setExpReward(15000)
                .setGoldReward(48000)
                .setTitle("Зеленая опасность!");
        questDealer.setEnemyCountToKill(16)
                .setEnemyToKill(new Goblin())
                .setEmployerName(dealerPetush.getName())
                .setEmployer(dealerPetush)
                .setConversationEmployer(questDialogConversationDealer);

        questDialogConversationDealer.setTitle(questDealer.getTitle());
        questDialogConversationDealer.setText("Иди убей 16 гоблинов");
        questDialogConversationDealer.setPlayerText("У тебя есть для меня задание?");
        questDialogConversationDealer.setQuest(questDealer);
        QuestDialogConversation questDialogConversationDealer2 = new QuestDialogConversation();
        CollectItemQuest questDealer2 = new CollectItemQuest();
        questDealer2.setExpReward(78120)
                .setGoldReward(253000);
        questDealer2.setItem(new KingGoblinRing());
        questDealer2.setPlayer(player);
        questDealer2.setTitle("Король гоблинов")
                .setEmployerName(dealerPetush.getName())
                .setEmployer(dealerPetush);
        questDealer2.setItemCount(1);
        questDealer2.setConversationEmployer(questDialogConversationDealer2);
        questDialogConversationDealer2.setTitle(questDealer2.getTitle());
        questDialogConversationDealer2.setText("Да, тут где-то находится Король гоблинов, крайне сильная тварь, мне нужно его кольцо, говорят, что оно стоит немалых денег!");
        questDialogConversationDealer2.setPlayerText("У тебя есть еще что-нибудь для меня?");
        questDialogConversationDealer2.setQuest(questDealer2);
        questDialogConversationDealer2.setIsVisible(false);
        questDialogConversationDealer.addConversationBranch(questDialogConversationDealer2, 1);
        dealerPetush.addConversationDialog(3, questDialogConversationDealer);

        dealerPetush.addItemToInventory(
                new Sword(Material.ELVENMYTHRIL, Rarity.LEGENDARY, Grade.ARTIFACT, 3, WeaponType.TWO_HANDED),
                new Helmet(Material.ELVENMYTHRIL, Rarity.MYSTICAL, Grade.ARTIFACT, 1)
        );

        for (Item item : dealerPetush.getInventory()) {
            item.countProperty();
        }
        dealerPetush.setX(1);
        dealerPetush.setY(1);


        dealerShutep = new Dealer(3,3,"Шутеп", 15623, 8461315);
        dealerShutep.setX(3);
        dealerShutep.setY(3);
        Sword shutepSwordForSale = new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED);
        shutepSwordForSale.countProperty();
        dealerShutep.addItemToInventory(new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED).countProperty(), new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED).countProperty(), new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED).countProperty(), new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED).countProperty());
        dealerShutep.addConversationShop(1);
        dealerShutep.addConversationTrain(2, "Тренировка", new Object[] {new DamageUp(), 99000, 1}, new Object[] {new DecreaseDamage(), 99000, 1}, new Object[] {new Vision(), 99000, 1}, new Object[] {new Rage(), 852000, 1});
        dealerShutep.getConversationWindow().setPlayer(player);

        inhabitantDanil = new Inhabitant(2,2,"Данил", 2, 140);
        inhabitantDanil.setStarterPhrase("Привет!");
        inhabitantDanil.addItemToInventory(new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion());
        inhabitantDanil.addConversationShop(1);
        inhabitantDanil.addConversationDialog(2, "Прощание", "Пока", "Прощай");


        DialogConversation hello = new DialogConversation();
        hello.setTitle("Приветствие");
        hello.setText("Привет");
        hello.setPlayerText("Привет");
        DialogConversation dialogConversation = new DialogConversation();
        dialogConversation.setTitle("Как дела?");
        dialogConversation.setText("Хорошо");
        dialogConversation.setPlayerText("Как дела?");
        DialogConversation dialogConversation2 = new DialogConversation();
        dialogConversation2.setTitle("Точно?");
        dialogConversation2.setText("Да");
        dialogConversation2.setPlayerText("Точно?");
        dialogConversation.addConversationBranch(dialogConversation2, 1);
        hello.addConversationBranch(dialogConversation, 3);
        QuestDialogConversation questDialogConversation = new QuestDialogConversation();

        KillQuest quest = new KillQuest();
        quest.setExpReward(2000);
        quest.setGoldReward(15000);
        quest.setTitle("Бандиты атакуют!");
        quest.setEnemyCountToKill(6);
        quest.setEnemyToKill(new Bandit());
        quest.setEmployerName(inhabitantDanil.getName());
        quest.setEmployer(inhabitantDanil);
        quest.setConversationEmployer(questDialogConversation);

        questDialogConversation.setTitle(quest.getTitle());
        questDialogConversation.setText("У нас проблемы с бандитами, иди убей для меня полдюжины");
        questDialogConversation.setPlayerText("У тебя есть для меня задание?");
        questDialogConversation.setQuest(quest);
        dialogConversation2.addConversationBranch(questDialogConversation, 1);

        QuestDialogConversation questDialogConversation2 = new QuestDialogConversation();
        ReachQuest dungeonQuest = new ReachQuest("Подземелье");
        dungeonQuest.setExpReward(31000);
        dungeonQuest.setGoldReward(115000);
        dungeonQuest.setTitle("Странные двери");
        dungeonQuest.setEmployerName(inhabitantDanil.getName());
        dungeonQuest.setEmployer(inhabitantDanil);
        dungeonQuest.setConversationEmployer(questDialogConversation2);


        KillQuest killQuestFrank = new KillQuest();
        killQuestFrank.setExpReward(170000);
        killQuestFrank.setGoldReward(570000);
        killQuestFrank.setTitle("Убить Франка");
        killQuestFrank.setEnemyCountToKill(1);
        killQuestFrank.setEnemyToKill(new Frank());
        killQuestFrank.setEmployerName(inhabitantDanil.getName());
        killQuestFrank.setEmployer(inhabitantDanil);
        killQuestFrank.setConversationEmployer(questDialogConversation2);

        KillQuest killQuestHigherGhost = new KillQuest();
        killQuestHigherGhost.setExpReward(170000);
        killQuestHigherGhost.setGoldReward(570000);
        killQuestHigherGhost.setTitle("Убить Высшего Призрака");
        killQuestHigherGhost.setEnemyCountToKill(1);
        killQuestHigherGhost.setEnemyToKill(new HigherGhost());
        killQuestHigherGhost.setEmployerName(inhabitantDanil.getName());
        killQuestHigherGhost.setEmployer(inhabitantDanil);
        killQuestHigherGhost.setConversationEmployer(questDialogConversation2);

        KillQuest killQuestDeadGuardian = new KillQuest();
        killQuestDeadGuardian.setExpReward(170000);
        killQuestDeadGuardian.setGoldReward(570000);
        killQuestDeadGuardian.setTitle("Убить Мертвого Стража");
        killQuestDeadGuardian.setEnemyCountToKill(1);
        killQuestDeadGuardian.setEnemyToKill(new DeadGuardian());
        killQuestDeadGuardian.setEmployerName(inhabitantDanil.getName());
        killQuestDeadGuardian.setEmployer(inhabitantDanil);
        killQuestDeadGuardian.setConversationEmployer(questDialogConversation2);


        Quest reachTwentyLevel = new Quest()
                .setCondition((QuestCondition & Serializable) () -> player.getLvl() >= 20)
                .setConditionToBeVisible((VisibleCondition & Serializable) () -> killQuestDeadGuardian.getIsFinished() && killQuestFrank.getIsFinished() && killQuestHigherGhost.getIsFinished())
                .setGoldReward(345000)
                .setExpReward(110000)
                .setTitle("Достичь 20 уровня")
                .setEmployerName("Вы");

        killQuestDeadGuardian.addAutoNextQuest(reachTwentyLevel);

        dungeonQuest
                .addAutoNextQuest(killQuestFrank)
                .addAutoNextQuest(killQuestHigherGhost)
                .addAutoNextQuest(killQuestDeadGuardian);

        questDialogConversation2.setTitle(dungeonQuest.getTitle());
        questDialogConversation2.setText("Ты встречал двери на своем пути? Это действительно что-то странное и загадочное. Конечно, немного странно просить того, кого видишь второй раз в жизни пойти туда, сам не знаешь куда, но я НИП, а поэтому мне плевать, вали все на разраба. Так вот о чем я, иди и исследуй эти двери и то, что за ними находится, может быть ты даже встретишь меня там, а может уже и не меня, кто знает");
        questDialogConversation2.setPlayerText("Бандиты убиты, что-нибудь еще?");
        questDialogConversation2.setQuest(dungeonQuest);
        questDialogConversation2.setIsVisible(false);
        questDialogConversation.addConversationBranch(questDialogConversation2, 1);

        inhabitantDanil.addConversationDialog(3, hello);

        inhabitantDanil.getConversationWindow().setPlayer(player);
        inhabitantDanil.setX(2);
        inhabitantDanil.setY(2);


        blacksmithDroghan = new BlackSmithCraftMan(7,7,"Дрогхан", 15623, 8461315);
        blacksmithDroghan.setX(7);
        blacksmithDroghan.setY(7);
        shutepSwordForSale.countProperty();
        blacksmithDroghan.addItemToInventory(
                new BluePrint()
                        .setType(ItemCraftType.SWORD_ONE_HANDED)
                        .addResource(new Adamantine())
                        .setName("Чертеж адамантинового меча")
                        .setCost(892000)
                        .setTemperature(3000),
                new BluePrint()
                        .setType(ItemCraftType.SWORD_ONE_HANDED)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового меча")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.AXE)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Топора")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.BOW)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Лука")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.LONGBOW)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Длинного лука")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.SHORT_BOW)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Короткого лука")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.STAFF)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Посоха")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.RING)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Кольца")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.HELMET)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Шлема")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.TORSO)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Доспеха")
                        .setCost(150000)
                        .setTemperature(1300),
                new Pickaxe().setCost(10000)
        );
        blacksmithDroghan.addConversationShop(1);
        blacksmithDroghan.addConversationTrain(2, "Тренировка",
                new Object[] {new BlackSmith(), 99000, 1}
                );
        blacksmithDroghan.getConversationWindow().setPlayer(player);
    }
}
