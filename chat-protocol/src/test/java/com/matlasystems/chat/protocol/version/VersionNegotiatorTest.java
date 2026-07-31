package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link VersionNegotiator}.
 */
class VersionNegotiatorTest {

    private final VersionNegotiator negotiator =
            new VersionNegotiator();

    @Test
    void negotiatesTheHighestCommonVersion() {

        ProtocolVersion version100 =
                ProtocolVersion.of(
                        1,
                        0);

        ProtocolVersion version120 =
                ProtocolVersion.of(
                        1,
                        2);

        ProtocolVersion version200 =
                ProtocolVersion.of(
                        2,
                        0);

        Set<ProtocolVersion> clientVersions =
                Set.of(
                        version100,
                        version120);

        Set<ProtocolVersion> serverVersions =
                Set.of(
                        version100,
                        version120,
                        version200);

        assertEquals(
                version120,
                negotiator.negotiate(
                        clientVersions,
                        serverVersions));

    }

    @Test
    void throwsWhenNoCommonVersionExists() {

        ProtocolVersion clientVersion =
                ProtocolVersion.of(
                        1,
                        0);

        ProtocolVersion serverVersion =
                ProtocolVersion.of(
                        2,
                        0);

        Set<ProtocolVersion> clientVersions =
                Set.of(
                        clientVersion);

        Set<ProtocolVersion> serverVersions =
                Set.of(
                        serverVersion);

        UnsupportedProtocolVersionException exception =
                assertThrows(
                        UnsupportedProtocolVersionException.class,
                        () -> negotiator.negotiate(
                                clientVersions,
                                serverVersions));

        assertNotNull(
                exception);

    }

    @Test
    void rejectsEmptyOrNullVersionSets() {

        ProtocolVersion version100 =
                ProtocolVersion.of(
                        1,
                        0);

        Set<ProtocolVersion> emptyVersions =
                Set.of();

        Set<ProtocolVersion> supportedVersions =
                Set.of(
                        version100);

        IllegalArgumentException emptyClientException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> negotiator.negotiate(
                                emptyVersions,
                                supportedVersions));

        assertNotNull(
                emptyClientException);

        IllegalArgumentException emptyServerException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> negotiator.negotiate(
                                supportedVersions,
                                emptyVersions));

        assertNotNull(
                emptyServerException);

    }

    @Test
    void negotiatesTheHighestCompatibleServerVersionForARequestedVersion() {

        ProtocolVersion version100 =
                ProtocolVersion.of(
                        1,
                        0);

        ProtocolVersion version120 =
                ProtocolVersion.of(
                        1,
                        2);

        ProtocolVersion version150 =
                ProtocolVersion.of(
                        1,
                        5);

        Set<ProtocolVersion> serverVersions =
                Set.of(
                        version100,
                        version150);

        assertEquals(
                version150,
                negotiator.negotiate(
                        version120,
                        serverVersions));

    }

    @Test
    void throwsWhenNoServerVersionIsCompatibleWithTheRequestedVersion() {

        ProtocolVersion requestedVersion =
                ProtocolVersion.of(
                        1,
                        0);

        ProtocolVersion serverVersion =
                ProtocolVersion.of(
                        2,
                        0);

        Set<ProtocolVersion> serverVersions =
                Set.of(
                        serverVersion);

        UnsupportedProtocolVersionException exception =
                assertThrows(
                        UnsupportedProtocolVersionException.class,
                        () -> negotiator.negotiate(
                                requestedVersion,
                                serverVersions));

        assertNotNull(
                exception);

    }

    @Test
    void rejectsANullRequestedVersion() {

        ProtocolVersion supportedVersion =
                ProtocolVersion.of(
                        1,
                        0);

        Set<ProtocolVersion> serverVersions =
                Set.of(
                        supportedVersion);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> negotiator.negotiate(
                                (ProtocolVersion) null,
                                serverVersions));

        assertNotNull(
                exception);

    }

}
