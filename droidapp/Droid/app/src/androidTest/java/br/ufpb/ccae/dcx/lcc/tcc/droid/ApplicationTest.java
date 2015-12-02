package br.ufpb.ccae.dcx.lcc.tcc.droid;


import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;

import android.test.AndroidTestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ApplicationTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());
    }

    public void testMockito() {


        Location mockedLocation = mock(Location.class);
        when(mockedLocation.getLatitude()).thenReturn(-27.000).thenReturn(-5.000);

        assertNotNull(mockedLocation);
        assertEquals(-27.000, mockedLocation.getLatitude());
        assertEquals(-5.000, mockedLocation.getLatitude());

    }

}
