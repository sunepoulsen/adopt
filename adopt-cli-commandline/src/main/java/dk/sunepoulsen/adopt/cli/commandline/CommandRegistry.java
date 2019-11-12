package dk.sunepoulsen.adopt.cli.commandline;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class CommandRegistry {
    private List<CommandDefinition> commands;

    public CommandRegistry() {
        this.commands = loadCommandsFromProviders();
    }

    public Stream<CommandDefinition> stream() {
        return commands.stream();
    }

    public Optional<CommandDefinition> findCommand( String name) {
        return commands.stream()
            .filter( commandDefinition -> commandDefinition.name().equals( name ) )
            .findFirst();
    }

    private List<CommandDefinition> loadCommandsFromProviders() {
        ServiceLoader<CommandDefinition> loader = ServiceLoader.load( CommandDefinition.class );

        return Lists.newArrayList( loader.iterator() );
    }
}
