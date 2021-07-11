package constructortool;

import creature.GodCreature;
import creature.LiveCreature;
import creature.peaceful.Peaceful;
import creature.Stats;
import creature.StatsEnum;
import item.alchemy.potion.HealPotion;
import item.alchemy.potion.PoisonPotion;
import item.armor.Helmet;
import item.armor.Torso;
import item.Grade;
import item.Item;
import item.Material;
import item.Rarity;
import item.weapon.bow.Bow;
import item.weapon.bow.LongBow;
import item.weapon.bow.ShortBow;
import item.weapon.chop.Axe;
import item.weapon.staff.Staff;
import item.weapon.sword.Sword;
import thing.chest.Chest;
import window.player.UnfocusedButton;
import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EditorWindow extends JFrame {

    private final GodCreature godCreature;
    private JPanel panel = new JPanel(new GridBagLayout());

    public EditorWindow(GodCreature godCreature){
        super("Редактирование " + godCreature.getName());

        this.godCreature = godCreature;

        drawWindow();
    }

    private void drawWindow(){
        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridy = 0;
        Dimension preferredSize = new Dimension(150, 20);

        if (godCreature instanceof LiveCreature creature) {

            Pair<String, JTextArea> name = printTextArea(new Pair<>("name", "Имя: "), constraints, preferredSize);

            JLabel stats = new JLabel("Статы: ");
            panel.add(stats, constraints);
            constraints.gridy ++;

            List<Pair<StatsEnum, JTextArea>> statList = Stream.of(
                    new Pair<>(StatsEnum.MAX_HP, "Жизни: "),
                    new Pair<>(StatsEnum.LEVEL, "Уровень: "),
                    new Pair<>(StatsEnum.STRENGTH, "Сила: "),
                    new Pair<>(StatsEnum.SPEED ,"Скорость: "),
                    new Pair<>(StatsEnum.AGILITY ,"Ловкость: "),
                    new Pair<>(StatsEnum.INTELLIGENCE ,"Разум: "),
                    new Pair<>(StatsEnum.LUCK ,"Удача: "),
                    new Pair<>(StatsEnum.ELOQUENCE ,"Красноречие: "),
                    new Pair<>(StatsEnum.BLACKSMITH ,"Кузнечное дело: "),
                    new Pair<>(StatsEnum.THEFT ,"Воровство: "),
                    new Pair<>(StatsEnum.ALCHEMY ,"Алхимия: "),
                    new Pair<>(StatsEnum.ONE_HANDED_WEAPON,"Одноручное оружие: "),
                    new Pair<>(StatsEnum.TWO_HANDED_WEAPON,"Двуручное оружие: "),
                    new Pair<>(StatsEnum.POLE_WEAPON,"Древковое оружие: "),
                    new Pair<>(StatsEnum.CHOPPING_WEAPON,"Рубящее оружие: "),
                    new Pair<>(StatsEnum.LONG_RANGE_WEAPON,"Дальнобойное оружие: ")
            ).map(e -> printTextArea(e, constraints, preferredSize)).collect(Collectors.toList());

            constraints.gridx = 0;
            UnfocusedButton countStats = new UnfocusedButton("Добавить");
            panel.add(countStats, constraints);
            constraints.gridy ++;

            countStats.addActionListener(e -> {
                creature.setName(name.second().getText());
                statList.forEach(pair -> {
                    Stats creatureStats = creature.getStats();
                    creatureStats.setStat(pair.first(), pair.second().getText());
                    pair.second().setText(Integer.toString(creatureStats.getStat(pair.first())));
                    if (pair.first() == StatsEnum.LEVEL) {
                        creature.countStatsAfterBorn();
                    }
                });
            });

        }

        JLabel itemTypeName = new JLabel("Предмет: ");
        panel.add(itemTypeName, constraints);
        constraints.gridx ++;
        List<Item> items = List.of(
                new Sword(),
                new Torso(),
                new Helmet(),
                new HealPotion(),
                new PoisonPotion(),
                new Bow(),
                new LongBow(),
                new ShortBow(),
                new Axe(),
                new Staff()
        );
        Vector<String> itemList = items.stream().map(Item::getName).collect(Collectors.toCollection(Vector::new));
        JComboBox<String> itemType = new JComboBox<>(itemList);
        panel.add(itemType, constraints);
        constraints.gridy ++;

        JComboBox<Material> itemMaterial = printComboBox(Material.values(), "Материал: ", constraints);
        JComboBox<Rarity> itemRarity = printComboBox(Rarity.values(), "Редкость: ", constraints);
        JComboBox<Grade> itemGrade = printComboBox(Grade.values(), "Качество: ", constraints);

        constraints.gridx = 0;
        UnfocusedButton addItem = new UnfocusedButton("Добавить в инвентарь");
        panel.add(addItem, constraints);

        addItem.addActionListener(e -> {
            Item item = items.get(itemType.getSelectedIndex());

            item.setMaterial(Material.values()[itemMaterial.getSelectedIndex()]);
            item.setRarity(Rarity.values()[itemRarity.getSelectedIndex()]);
            item.setGrade(Grade.values()[itemGrade.getSelectedIndex()]);
            item.countProperty();
            if (godCreature instanceof Chest chest) {
                chest.addItemToInventory(item);
            } else if (godCreature instanceof LiveCreature creature) {
                creature.addItemToInventory(item);
            }
        });
        if(godCreature instanceof Peaceful peaceful){
            ConstructorConversationWindow constructorConversationWindow = new ConstructorConversationWindow(peaceful);
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    private <T> Pair<T, JTextArea> printTextArea(Pair<T, String> pair, GridBagConstraints constraints, Dimension preferredSize) {
        constraints.gridx = 0;
        JLabel jText = new JLabel(pair.second());
        panel.add(jText, constraints);
        constraints.gridx ++;
        JTextArea jTextArea = new JTextArea();
        jTextArea.setPreferredSize(preferredSize);
        jTextArea.setMaximumSize(preferredSize);
        jTextArea.setMinimumSize(preferredSize);
        panel.add(jTextArea, constraints);
        constraints.gridy ++;
        return new Pair<>(pair.first(), jTextArea);
    }

    private <T> JComboBox<T> printComboBox(T[] values, String label, GridBagConstraints constraints) {
        constraints.gridx = 0;
        JLabel itemGradeName = new JLabel(label);
        panel.add(itemGradeName, constraints);
        constraints.gridx ++;
        JComboBox<T> comboBox = new JComboBox<>(values);
        panel.add(comboBox, constraints);
        constraints.gridy ++;
        return comboBox;
    }
}
