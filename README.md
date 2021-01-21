# RolePlay ![Maven Build](https://github.com/chibbi/RolePlay/workflows/Maven%20Build/badge.svg?branch=main&event=push) ![pre-release](https://github.com/chibbi/RolePlay/workflows/pre-release/badge.svg?branch=main)
RolePlay is a Spigot plugin that adds skill trees, classes that you have to commit to and a financial system.
It currently let's you choose one job, in which you specialize, and you depend on others to have different jobs, to be able to progress.  
#### USES JRE 11  

## May rewrite all Listeners, because i forgot, you could cancel events
## Needs a wiki  
  
## Possibilities:
#### config/defaultconfig:
pvpmode: if enabled (true) you don't loose your xp and jobs on a kill
imperatormode: should always stay true
#### Jobconfig:
```
JOBNAME:
  effects:
    positives:
    - POSITIVE EFFECTS
    - POSITIVE EFFECTS
    negatives:
    - NEGATIVE EFFECTS
```
JOBNAME: name of your job  
POSITIVE EFFECTS: positive potion effects  
NEGATIVE EFFECTS: negative potion effects  
just rename everything that is in caps to your liking.  
you can find [all possible effects here.](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/potion/PotionEffectType.html)
#### Xpconfig:
```
JOBNAME:
  break:
    BLOCKGROUP1:
      BLOCK: XP
      BLOCK: XP
    BLOCKGROUP2:
      BLOCK: XP
  place:
    BLOCKGROUP1:
      BLOCK: XP
      BLOCK: XP
  craft:
    BLOCKGROUP1:
      BLOCK: XP
      ITEM: XP
  playerkill:
    JOBGROUP1:
      JOBNAME: XP
      JOBNAME: XP
  mobkill:
    MOBGROUP1:
      MOBNAME: XP
      MOBNAME: XP
```
JOBNAME: name of your job  
BLOCKGROUP1: grouping of BLOCK for readability  
MOBNAME: name of the killed mob
BLOCK: your desired BLOCK  
ITEM: your desired ITEM  
XP: the amount of XP (int) the job should get, per break or place of the BLOCK  
the kill section, lets you configure, that the job, it is a part of, can get (or loose with negative int) when he kills someone part of the given JOBNAME  
  
```
JOBNAME:
  allowedTools:
    TOOLGROUP1:
      TOOLNAME: XP (the XP value is just for simplicity sake here, it won't do anything)
      MOBNAME: XP
  deniedTools:
    TOOLGROUP1:
      TOOLNAME: XP (the XP value is just for simplicity sake here, it won't do anything)
      TOOLNAME: XP
  denyCraft:
    TOOLGROUP1:
      TOOLNAME: XP (the XP value is just for simplicity sake here, it won't do anything)
      TOOLNAME: XP
```
allowedTools is a WhiteList for breaking blocks
deniedTools is a Blacklist for interacting with blocks
  
denyCraft is a Blacklist, with regex (ex.: "HOE" = all Items with HOE in their name are Blacklisted), for crafting  
allowCraft is a Whitelist for denyCraft (ex.: "WOODEN_HOE" allows you to craft a WOODEN_HOE but no other HOE (if HOE is set in denyCraft))  
  
you can use just one big jobgroup, but for readability,
you can group them to your liking.  
you can use just one big blockgroup, but for readability,
you can group them to your liking.  
just rename everything that is in caps to your liking.  
you can find [all possible Materials (Blocks and Items) here.](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)
you can find [all possible Effects/Potions here.](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/potion/PotionEffectType.html)

## Contributors:
 - whole Job System: [chibbi](https://github.com/chibbi)
 - whole Finance System: [raninninn](https://github.com/raninninn)
## Notices:  
 - [GUI manager?](https://www.spigotmc.org/wiki/creating-a-gui-inventory/)
 - [Command Completer?(Tabcomplete)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/server/TabCompleteEvent.html)
 - [no cobble generator](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/block/BlockFormEvent.html)
 
 - MC Colors:
  - &f = white
  - &0 = black
  - &1 = dar kblue
  - &2 = dark green
  - &a = light green
  - &3 = grayish blue ? 
  - &b = light blue
  - &4 = red
  - &c = light red
  - &5 = pink
  - &c = light pink
  - &6 = brown/orange
  - &e = yellow
  - &7 = grey
  - &8 = dark grey
  - &9 = another blue

 - Logger levels:
  - SEVERE (highest value)
  - WARNING
  - INFO
  - CONFIG
  - FINE
  - FINER
  - FINEST (lowest value)
