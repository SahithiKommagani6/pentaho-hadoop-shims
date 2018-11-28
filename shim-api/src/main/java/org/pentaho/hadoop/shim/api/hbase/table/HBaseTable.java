/*******************************************************************************
 *
 * Pentaho Big Data
 *
 * Copyright (C) 2002-2017 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.hadoop.shim.api.hbase.table;

import org.pentaho.hadoop.shim.api.hbase.mapping.Mapping;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogChannelInterface;
import org.pentaho.di.core.variables.VariableSpace;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by bryan on 1/19/16.
 */
public interface HBaseTable extends Closeable {
  boolean exists() throws IOException;

  boolean disabled() throws IOException;

  boolean available() throws IOException;

  void disable() throws IOException;

  void enable() throws IOException;

  void delete() throws IOException;

  void create( List<String> colFamilyNames, Properties creationProps ) throws IOException;

  ResultScannerBuilder createScannerBuilder( byte[] keyLowerBound, byte[] keyUpperBound );

  ResultScannerBuilder createScannerBuilder( Mapping tableMapping, String dateOrNumberConversionMaskForKey,
                                             String keyStartS, String keyStopS,
                                             String scannerCacheSize, LogChannelInterface log, VariableSpace vars )
    throws KettleException;

  List<String> getColumnFamilies() throws IOException;

  boolean keyExists( byte[] key ) throws IOException;

  HBaseTableWriteOperationManager createWriteOperationManager( Long writeBufferSize ) throws IOException;
}
