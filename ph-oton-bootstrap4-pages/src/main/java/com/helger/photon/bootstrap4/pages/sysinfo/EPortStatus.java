package com.helger.photon.bootstrap4.pages.sysinfo;

enum EPortStatus
{
  PORT_IS_OPEN,
  PORT_IS_CLOSED,
  HOST_NOT_EXISTING,
  CONNECTION_TIMEOUT,
  GENERIC_IO_ERROR;

  public boolean isPortOpen ()
  {
    return this == PORT_IS_OPEN;
  }
}