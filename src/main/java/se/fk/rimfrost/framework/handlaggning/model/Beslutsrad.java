package se.fk.rimfrost.framework.handlaggning.model;

import org.immutables.value.Value;
import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface Beslutsrad
{
   UUID id();

   int version();

   String beslutsTyp();

   String beslutsUtfall();

   String avslutsTyp();

   List<ProduceratResultatRef> produceradeResultatRef();

}
