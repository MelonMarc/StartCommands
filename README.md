# StartCommands

A plugin for Minecraft that automatically runs useful commands when a player joins.

With this plugin you don't need to type for instance //fast and /ptime @12pm every time you join a server. With this plugin you can decide which plugins you want to run when joining. The commands are individual for each player. StartCommands does of course support name changes using uuids. 

The commands are:

/startcommands: Run your startcommands.

/startcommands add command: Add a command to your startcommands.

/startcommands addp player command: Add a command to another player's startcommands.

/startcommands help: StartCommands help.

/startcommands info: StartCommands information.

/startcommands list [player]: A list of your or another player's startcommands.

/startcommands reload: Reload the StartCommands config.

/startcommands remove command: Remove a command from your startcommands.

/startcommands removep player command: Remove a command from another player's startcommands.

(instead of /startcommands you can use /sc)

The permission nodes are:

'*': Acces to all commands.

startcommands.*: Acces to all commands.

startcommands.player: Acces to all player startcommands commands.

startcommands.add: Add a command to your startcommands.

startcommands.addp: Add a command to another player's startcommands.

startcommands.help: StartCommands help.

startcommands.info: StartCommands info.

startcommands.list: A list of your startcommands.

startcommands.list.player: A list of another player's startcommands.

startcommands.reload: Reload the StartCommands config.

startcommands.remove: Remove a command from your startcommands.

startcommands.removep: Remove a command from another player's startcommands.

startcommands.run: Run your startcommands.
