# RolePlay ![Maven Build](https://github.com/chibbi/RolePlay/workflows/Maven%20Build/badge.svg?branch=main&event=push) ![pre-release](https://github.com/chibbi/RolePlay/workflows/pre-release/badge.svg?branch=main)
RolePlay is a Spigot plugin that is under very active development and adds jobs that you have to commit to ~~and a financial system~~.
It currently let's you choose one job, in which you specialize, and you depend on others to have different jobs, to be able to progress.    

**An example gameplay** would be, that you are a miner, who is the only one who can use good pickaxes. But nbecause your not good at forging, you need a blacksmith, who can craft you a good pickaxe, otherwise the blacksmith needs you, because you are the only one who can use the pickaxes and give him ores => something to work.    
There are also farmer, which will be the only ones who can efficiently give food, and hunters which will be the only ones who can efficiently hunt. it is not thought of, to completly deny hunting and farming for other players, but that is still in development.  
  
**You can add ANY job you want**, as long as you can realise it with the currently available configs. I am really trying to deliver a really modular easy to configure plugin, which has a lot of possibilities.
The currently available jobs, and what i am talking about, is just the way i use the plugin.  
If you have a job idea, but can't implement it yet, because of missing config features (or other features) i will gladly look into it, and try to implement it, if you [open a feature  request](https://github.com/chibbi/RolePlay/issues/new?assignees=&labels=feature&template=feature_request.md&title=).  
##### For more information, you can look into [the wiki](https://github.com/chibbi/RolePlay/wiki)
#### USES JRE 11  

#### Currently implemented by default:
| job | description | job | description |
| :--: | --------- | :--: | ----------- |
| farmer | specialized in farming plants | lumberjack | specialized in cutting and processing wood  |
| miner | specialized in mining ores, and stone | builder | specialized in moving dirt and building |
| hunter | specialized in hunting friendly mobs (for food) | warrior | specialized in hunting hostile mobs (for fun i guess xD) |
| knight | specialized in protecting villages and other players | assassin | specialized in killing other players (may do it for money) |
| blacksmith | specialized in processing ores | --- | --- |

#### Currently in development:
 - these here are jobs that need a specific event to be implemented to work
 - [20%] mage
 - [50%] hunter
 - [39%] farmer

#### Currently planned:
 - merchant
 - stonemason
 - painter
 - fisherman
  
you can find [all possible Materials (Blocks and Items) here.](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)  
you can find [all possible Effects/Potions here.](https://github.com/chibbi/RolePlay/wiki/Effects-Potions)  
you can find [all possible entities / mobs here.](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/EntityType.html)

## Contributors:
 - Job System: [chibbi](https://github.com/chibbi)
 - Finance System: [raninninn](https://github.com/raninninn)
