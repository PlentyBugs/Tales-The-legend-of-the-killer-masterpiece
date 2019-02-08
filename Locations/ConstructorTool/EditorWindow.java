package Locations.ConstructorTool;

import Items.Armors.Helmet;
import Items.Armors.Torso;
import Items.Grade;
import Items.Item;
import Items.Material;
import Items.Potions.HealPotion;
import Items.Potions.PoisonPotion;
import Items.Rarity;
import Items.Weapons.Bows.Bow;
import Items.Weapons.Bows.LongBow;
import Items.Weapons.Bows.ShortBow;
import Items.Weapons.Swords.Sword;
import LiveCreatures.GodCreature;
import LiveCreatures.LiveCreature;
import LiveCreatures.PeacefulNPC.Peaceful;
import Things.Chest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorWindow extends JFrame {

    private GodCreature godCreature;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;

    public EditorWindow(GodCreature godCreature){
        super("Редактирование " + godCreature.getName());

        this.godCreature = godCreature;

        drawWindow();
    }

    private void drawWindow(){
        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        if (!godCreature.getClass().toString().contains("Chest")){

            JLabel name = new JLabel("Имя: ");
            panel.add(name, constraints);
            constraints.gridx ++;
            JTextArea nameTextArea = new JTextArea();
            nameTextArea.setPreferredSize(new Dimension(150,20));
            nameTextArea.setMaximumSize(new Dimension(150,20));
            nameTextArea.setMinimumSize(new Dimension(150,20));
            panel.add(nameTextArea, constraints);
            constraints.gridy ++;

            JLabel stats = new JLabel("Статы: ");
            panel.add(stats, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel hpName = new JLabel("Жизни: ");
            panel.add(hpName, constraints);
            constraints.gridx ++;
            JTextArea hpValue = new JTextArea();
            hpValue.setPreferredSize(new Dimension(150,20));
            hpValue.setMaximumSize(new Dimension(150,20));
            hpValue.setMinimumSize(new Dimension(150,20));
            panel.add(hpValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel levelName = new JLabel("Уровень: ");
            panel.add(levelName, constraints);
            constraints.gridx ++;
            JTextArea levelValue = new JTextArea();
            levelValue.setPreferredSize(new Dimension(150,20));
            levelValue.setMaximumSize(new Dimension(150,20));
            levelValue.setMinimumSize(new Dimension(150,20));
            panel.add(levelValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel strengthName = new JLabel("Сила: ");
            panel.add(strengthName, constraints);
            constraints.gridx ++;
            JTextArea strengthValue = new JTextArea();
            strengthValue.setPreferredSize(new Dimension(150,20));
            strengthValue.setMaximumSize(new Dimension(150,20));
            strengthValue.setMinimumSize(new Dimension(150,20));
            panel.add(strengthValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel speedName = new JLabel("Скорость: ");
            panel.add(speedName, constraints);
            constraints.gridx ++;
            JTextArea speedValue = new JTextArea();
            speedValue.setPreferredSize(new Dimension(150,20));
            speedValue.setMaximumSize(new Dimension(150,20));
            speedValue.setMinimumSize(new Dimension(150,20));
            panel.add(speedValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel agilityName = new JLabel("Ловкость: ");
            panel.add(agilityName, constraints);
            constraints.gridx ++;
            JTextArea agilityValue = new JTextArea();
            agilityValue.setPreferredSize(new Dimension(150,20));
            agilityValue.setMaximumSize(new Dimension(150,20));
            agilityValue.setMinimumSize(new Dimension(150,20));
            panel.add(agilityValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel intelligenceName = new JLabel("Разум: ");
            panel.add(intelligenceName, constraints);
            constraints.gridx ++;
            JTextArea intelligenceValue = new JTextArea();
            intelligenceValue.setPreferredSize(new Dimension(150,20));
            intelligenceValue.setMaximumSize(new Dimension(150,20));
            intelligenceValue.setMinimumSize(new Dimension(150,20));
            panel.add(intelligenceValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel luckName = new JLabel("Удача: ");
            panel.add(luckName, constraints);
            constraints.gridx ++;
            JTextArea luckValue = new JTextArea();
            luckValue.setPreferredSize(new Dimension(150,20));
            luckValue.setMaximumSize(new Dimension(150,20));
            luckValue.setMinimumSize(new Dimension(150,20));
            panel.add(luckValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel eloquenceName = new JLabel("Красноречие: ");
            panel.add(eloquenceName, constraints);
            constraints.gridx ++;
            JTextArea eloquenceValue = new JTextArea();
            eloquenceValue.setPreferredSize(new Dimension(150,20));
            eloquenceValue.setMaximumSize(new Dimension(150,20));
            eloquenceValue.setMinimumSize(new Dimension(150,20));
            panel.add(eloquenceValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel blacksmithName = new JLabel("Кузнечное дело: ");
            panel.add(blacksmithName, constraints);
            constraints.gridx ++;
            JTextArea blacksmithValue = new JTextArea();
            blacksmithValue.setPreferredSize(new Dimension(150,20));
            blacksmithValue.setMaximumSize(new Dimension(150,20));
            blacksmithValue.setMinimumSize(new Dimension(150,20));
            panel.add(blacksmithValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel alchemyName = new JLabel("Алхимия: ");
            panel.add(alchemyName, constraints);
            constraints.gridx ++;
            JTextArea alchemyValue = new JTextArea();
            alchemyValue.setPreferredSize(new Dimension(150,20));
            alchemyValue.setMaximumSize(new Dimension(150,20));
            alchemyValue.setMinimumSize(new Dimension(150,20));
            panel.add(alchemyValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel one_handed_weaponName = new JLabel("Одноручное оружие: ");
            panel.add(one_handed_weaponName, constraints);
            constraints.gridx ++;
            JTextArea one_handed_weaponValue = new JTextArea();
            one_handed_weaponValue.setPreferredSize(new Dimension(150,20));
            one_handed_weaponValue.setMaximumSize(new Dimension(150,20));
            one_handed_weaponValue.setMinimumSize(new Dimension(150,20));
            panel.add(one_handed_weaponValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel two_handed_weaponName = new JLabel("Двуручное оруэие: ");
            panel.add(two_handed_weaponName, constraints);
            constraints.gridx ++;
            JTextArea two_handed_weaponValue = new JTextArea();
            two_handed_weaponValue.setPreferredSize(new Dimension(150,20));
            two_handed_weaponValue.setMaximumSize(new Dimension(150,20));
            two_handed_weaponValue.setMinimumSize(new Dimension(150,20));
            panel.add(two_handed_weaponValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel pole_weaponName = new JLabel("Древковое оружие: ");
            panel.add(pole_weaponName, constraints);
            constraints.gridx ++;
            JTextArea pole_weaponValue = new JTextArea();
            pole_weaponValue.setPreferredSize(new Dimension(150,20));
            pole_weaponValue.setMaximumSize(new Dimension(150,20));
            pole_weaponValue.setMinimumSize(new Dimension(150,20));
            panel.add(pole_weaponValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel chopping_weaponName = new JLabel("Рубящее оружие: ");
            panel.add(chopping_weaponName, constraints);
            constraints.gridx ++;
            JTextArea chopping_weaponValue = new JTextArea();
            chopping_weaponValue.setPreferredSize(new Dimension(150,20));
            chopping_weaponValue.setMaximumSize(new Dimension(150,20));
            chopping_weaponValue.setMinimumSize(new Dimension(150,20));
            panel.add(chopping_weaponValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel long_range_weaponName = new JLabel("Дальнобойное оружие: ");
            panel.add(long_range_weaponName, constraints);
            constraints.gridx ++;
            JTextArea long_range_weaponValue = new JTextArea();
            long_range_weaponValue.setPreferredSize(new Dimension(150,20));
            long_range_weaponValue.setMaximumSize(new Dimension(150,20));
            long_range_weaponValue.setMinimumSize(new Dimension(150,20));
            panel.add(long_range_weaponValue, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JButton countStats = new JButton("Добавить");
            panel.add(countStats, constraints);
            constraints.gridy ++;

            countStats.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try{
                        ((LiveCreature)godCreature).setLvl(Integer.parseInt(levelValue.getText()));
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).setLvl(1);
                    }
                    try{
                        ((LiveCreature)godCreature).setHp(Integer.parseInt(hpValue.getText()));
                        ((LiveCreature)godCreature).setMaxHp(Integer.parseInt(hpValue.getText()));
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).setHp(200);
                        ((LiveCreature)godCreature).setMaxHp(200);
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().strength = Integer.parseInt(strengthValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().strength = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().speed = Integer.parseInt(speedValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().speed = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().agility = Integer.parseInt(strengthValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().agility = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().intelligence = Integer.parseInt(intelligenceValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().intelligence = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().luck = Integer.parseInt(luckValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().luck = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().eloquence = Integer.parseInt(eloquenceValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().eloquence = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().blacksmith = Integer.parseInt(blacksmithValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().blacksmith = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().alchemy = Integer.parseInt(alchemyValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().alchemy = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().one_handed_weapon = Integer.parseInt(one_handed_weaponValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().one_handed_weapon = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().two_handed_weapon = Integer.parseInt(two_handed_weaponValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().two_handed_weapon = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().pole_weapon = Integer.parseInt(pole_weaponValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().pole_weapon = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().chopping_weapon = Integer.parseInt(chopping_weaponValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().chopping_weapon = 5;
                    }
                    try{
                        ((LiveCreature)godCreature).getStats().long_range_weapon = Integer.parseInt(long_range_weaponValue.getText());
                    } catch (Exception ex){
                        ((LiveCreature)godCreature).getStats().long_range_weapon = 5;
                    }
                }
            });

        }

        if(!godCreature.getClass().toString().contains("Peaceful")){
            JLabel itemTypeName = new JLabel("Оружие: ");
            panel.add(itemTypeName, constraints);
            constraints.gridx ++;
            JComboBox itemType = new JComboBox(new String[]{new Sword().getName(), new Torso().getName(), new Helmet().getName(), new HealPotion().getName(), new PoisonPotion().getName(), new Bow().getName(), new LongBow().getName(), new ShortBow().getName()});
            panel.add(itemType, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel itemMaterialName = new JLabel("Материал: ");
            panel.add(itemMaterialName, constraints);
            constraints.gridx ++;
            JComboBox itemMaterial = new JComboBox(Material.values());
            panel.add(itemMaterial, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel itemRarityName = new JLabel("Редкость: ");
            panel.add(itemRarityName, constraints);
            constraints.gridx ++;
            JComboBox itemRarity = new JComboBox(Rarity.values());
            panel.add(itemRarity, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JLabel itemGradeName = new JLabel("Качество: ");
            panel.add(itemGradeName, constraints);
            constraints.gridx ++;
            JComboBox itemGrade = new JComboBox(Grade.values());
            panel.add(itemGrade, constraints);
            constraints.gridy ++;

            constraints.gridx = 0;
            JButton addItem = new JButton("Добавить");
            panel.add(addItem, constraints);

            addItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Item item = new Item();
                    if (itemType.getSelectedIndex() == 0){
                        item = new Sword();
                    } else if (itemType.getSelectedIndex() == 1){
                        item = new Torso();
                    } else if (itemType.getSelectedIndex() == 2){
                        item = new Helmet();
                    } else if (itemType.getSelectedIndex() == 3){
                        item = new HealPotion();
                    } else if (itemType.getSelectedIndex() == 4){
                        item = new PoisonPotion();
                    }
                    item.setMaterial(Material.values()[itemMaterial.getSelectedIndex()]);
                    item.setRarity(Rarity.values()[itemRarity.getSelectedIndex()]);
                    item.setGrade(Grade.values()[itemGrade.getSelectedIndex()]);
                    item.countProperty();
                    if (godCreature.getClass().toString().split("\\.")[1].split(" ")[0].equals("Chest")){
                        ((Chest)godCreature).addItemToInventory(item);
                    } else {
                        ((LiveCreature)godCreature).addToUniqueDropItem(item);
                    }
                }
            });
        }
        if(godCreature.getClass().toString().contains("Peaceful")){
            ConstructorConversationWindow constructorConversationWindow = new ConstructorConversationWindow((Peaceful)godCreature);
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
