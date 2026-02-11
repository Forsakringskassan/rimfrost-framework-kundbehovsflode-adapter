package se.fk.rimfrost.framework.kundbehovsflode.adapter.dto;

import org.immutables.value.Value;
import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface UpdateKundbehovsflodeRequest
{
   UUID kundbehovsflodeId();

   UpdateKundbehovsflodeUppgift uppgift();

   List<UpdateKundbehovsflodeErsattning> ersattningar();

   List<UpdateKundbehovsflodeUnderlag> underlag();

}
