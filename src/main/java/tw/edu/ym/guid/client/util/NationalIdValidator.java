package tw.edu.ym.guid.client.util;

import static net.sf.rubycollect4j.RubyCollections.Hash;
import static net.sf.rubycollect4j.RubyCollections.hp;
import static net.sf.rubycollect4j.RubyCollections.ra;
import static net.sf.rubycollect4j.RubyCollections.range;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import net.sf.rubycollect4j.RubyArray;
import net.sf.rubycollect4j.block.ReduceBlock;
import net.sf.rubycollect4j.block.TransformBlock;

import com.google.common.collect.ImmutableMap;

public enum NationalIdValidator {

  INSTANCE;

  private final Map<Character, Integer> PREFIX;

  @SuppressWarnings("unchecked")
  private NationalIdValidator() {
    RubyArray<String> ints =
        range("10", "17").toA().push("34").concat(range("18", "22").toA())
            .push("35").concat(range("23", "29").toA()).push("32")
            .concat(range("30", "31").toA()).push("33");
    PREFIX =
        ImmutableMap.copyOf(Hash(range("A", "Z").zip(ints).map(
            new TransformBlock<RubyArray<String>, Entry<Character, Integer>>() {

              public Entry<Character, Integer> yield(RubyArray<String> item) {
                return hp(item.first().charAt(0), Integer.valueOf(item.last()));
              }

            })));
  }

  public boolean validate(String nationalId) {
    if (nationalId == null
        || !Pattern.compile("^[A-Z]\\d{9}$").matcher(nationalId).find())
      return false;

    RubyArray<Integer> ints =
        ra(nationalId.substring(1).split("(?!^)")).map(
            new TransformBlock<String, Integer>() {

              public Integer yield(String item) {
                return Integer.valueOf(item);
              }

            });
    ints.unshift(PREFIX.get(nationalId.charAt(0)) % 10);
    ints.unshift(PREFIX.get(nationalId.charAt(0)) / 10);

    int sum =
        ints.first()
            + ints.slice(1, 9).eachWithIndex()
                .map(new TransformBlock<Entry<Integer, Integer>, Integer>() {

                  public Integer yield(Entry<Integer, Integer> item) {
                    return item.getKey() * (9 - item.getValue());
                  }

                }).reduce(new ReduceBlock<Integer>() {

                  public Integer yield(Integer memo, Integer item) {
                    return memo + item;
                  }

                });

    int mod = sum % 10;
    int checksum = mod == 0 ? mod : 10 - mod;

    return ints.last() == checksum;
  }

}
