package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameMatchesQueryNamePredicate implements Predicate<Person> {
    private final Name queryName;

    public NameMatchesQueryNamePredicate(Name queryName) {
        this.queryName = queryName;
    }

    @Override
    public boolean test(Person person) {
        return person.getName().equals(queryName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameMatchesQueryNamePredicate)) {
            return false;
        }

        NameMatchesQueryNamePredicate otherNameMatchesQueryNamePredicate = (NameMatchesQueryNamePredicate) other;
        return queryName.equals(otherNameMatchesQueryNamePredicate.queryName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("queryName", queryName).toString();
    }
}
