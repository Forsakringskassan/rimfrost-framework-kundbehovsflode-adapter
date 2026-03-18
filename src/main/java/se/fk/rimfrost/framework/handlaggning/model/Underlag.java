package se.fk.rimfrost.framework.handlaggning.model;

import org.immutables.value.Value;

@Value.Immutable
public interface Underlag
{
   String typ();

   Integer version();

   String data();
}
