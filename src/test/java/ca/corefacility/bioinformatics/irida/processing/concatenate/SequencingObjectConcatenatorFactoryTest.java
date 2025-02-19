package ca.corefacility.bioinformatics.irida.processing.concatenate;

import ca.corefacility.bioinformatics.irida.model.sequenceFile.SequenceFilePair;
import ca.corefacility.bioinformatics.irida.model.sequenceFile.SequencingObject;
import ca.corefacility.bioinformatics.irida.model.sequenceFile.SingleEndSequenceFile;
import ca.corefacility.bioinformatics.irida.processing.concatenate.impl.SequenceFilePairConcatenator;
import ca.corefacility.bioinformatics.irida.processing.concatenate.impl.SingleEndSequenceFileConcatenator;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for {@link SequencingObjectConcatenatorFactory}
 */
public class SequencingObjectConcatenatorFactoryTest {
	@Test
	public void testGetConcatenatorSingle() {
		SequencingObjectConcatenator<SingleEndSequenceFile> concatenator = SequencingObjectConcatenatorFactory.getConcatenator(
				SingleEndSequenceFile.class);
		assertTrue(concatenator instanceof SingleEndSequenceFileConcatenator);
	}

	@Test
	public void testGetConcatenatorPair() {
		SequencingObjectConcatenator<SequenceFilePair> concatenator = SequencingObjectConcatenatorFactory.getConcatenator(
				SequenceFilePair.class);
		assertTrue(concatenator instanceof SequenceFilePairConcatenator);
	}

	@Test
	public void testGetConcatenatorError() {
		assertThrows(IllegalArgumentException.class, () -> {
			SequencingObjectConcatenatorFactory.getConcatenator(SequencingObject.class);
		});
	}

	@Test
	public void testGetConcatenatorSingleCollection() {
		Set<SingleEndSequenceFile> fileSet = Sets.newHashSet(new SingleEndSequenceFile(null));
		SequencingObjectConcatenator<?> concatenator = SequencingObjectConcatenatorFactory.getConcatenator(fileSet);
		assertTrue(concatenator instanceof SingleEndSequenceFileConcatenator);
	}

	@Test
	public void testGetConcatenatorPairCollection() {
		Set<SequenceFilePair> fileSet = Sets.newHashSet(new SequenceFilePair());
		SequencingObjectConcatenator<?> concatenator = SequencingObjectConcatenatorFactory.getConcatenator(fileSet);
		assertTrue(concatenator instanceof SequenceFilePairConcatenator);
	}

	@Test
	public void testGetConcatenatorMixedError() {
		Set<SequencingObject> fileSet = Sets.newHashSet(new SequenceFilePair(), new SingleEndSequenceFile(null));
		assertThrows(IllegalArgumentException.class, () -> {
			SequencingObjectConcatenatorFactory.getConcatenator(fileSet);
		});
	}
}
