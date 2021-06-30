package creature.peaceful.npc;

import conversation.DialogConversation;
import conversation.QuestDialogConversation;
import creature.Player;
import creature.aggressive.Bandit;
import creature.aggressive.boss.DeadGuardian;
import creature.aggressive.boss.Frank;
import creature.aggressive.boss.HigherGhost;
import creature.peaceful.Inhabitant;
import item.alchemy.potion.HealPotion;
import quest.*;
import support.CreatureProperty;
import support.Property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DanilNPC extends Inhabitant {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Inhabitant.propertyList);
        propertyList.add(CreatureProperty.DANIL);
    }

    public DanilNPC(Player player) {
        new Inhabitant(2,2,"Данил", 2, 140);
        setStarterPhrase("Привет!");
        addItemToInventory(new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion());
        addConversationShop(1);
        addConversationDialog(2, "Прощание", "Пока", "Прощай");

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
        quest.setEmployerName(getName());
        quest.setEmployer(this);
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
        dungeonQuest.setEmployerName(getName());
        dungeonQuest.setEmployer(this);
        dungeonQuest.setConversationEmployer(questDialogConversation2);


        KillQuest killQuestFrank = new KillQuest();
        killQuestFrank.setExpReward(170000);
        killQuestFrank.setGoldReward(570000);
        killQuestFrank.setTitle("Убить Франка");
        killQuestFrank.setEnemyCountToKill(1);
        killQuestFrank.setEnemyToKill(new Frank());
        killQuestFrank.setEmployerName(getName());
        killQuestFrank.setEmployer(this);
        killQuestFrank.setConversationEmployer(questDialogConversation2);

        KillQuest killQuestHigherGhost = new KillQuest();
        killQuestHigherGhost.setExpReward(170000);
        killQuestHigherGhost.setGoldReward(570000);
        killQuestHigherGhost.setTitle("Убить Высшего Призрака");
        killQuestHigherGhost.setEnemyCountToKill(1);
        killQuestHigherGhost.setEnemyToKill(new HigherGhost());
        killQuestHigherGhost.setEmployerName(getName());
        killQuestHigherGhost.setEmployer(this);
        killQuestHigherGhost.setConversationEmployer(questDialogConversation2);

        KillQuest killQuestDeadGuardian = new KillQuest();
        killQuestDeadGuardian.setExpReward(170000);
        killQuestDeadGuardian.setGoldReward(570000);
        killQuestDeadGuardian.setTitle("Убить Мертвого Стража");
        killQuestDeadGuardian.setEnemyCountToKill(1);
        killQuestDeadGuardian.setEnemyToKill(new DeadGuardian());
        killQuestDeadGuardian.setEmployerName(getName());
        killQuestDeadGuardian.setEmployer(this);
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

        addConversationDialog(3, hello);

        getConversationWindow().setPlayer(player);
    }
}
