/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\Eigene Dateien\\workspace\\CarSimulator\\src\\de\\tudarmstadt\\kom\\carsimulator\\SimulationEngineAPI.aidl
 */
package de.tudarmstadt.kom.carsimulator;
public interface SimulationEngineAPI extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements de.tudarmstadt.kom.carsimulator.SimulationEngineAPI
{
private static final java.lang.String DESCRIPTOR = "de.tudarmstadt.kom.carsimulator.SimulationEngineAPI";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an de.tudarmstadt.kom.carsimulator.SimulationEngineAPI interface,
 * generating a proxy if needed.
 */
public static de.tudarmstadt.kom.carsimulator.SimulationEngineAPI asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof de.tudarmstadt.kom.carsimulator.SimulationEngineAPI))) {
return ((de.tudarmstadt.kom.carsimulator.SimulationEngineAPI)iin);
}
return new de.tudarmstadt.kom.carsimulator.SimulationEngineAPI.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getCurrentPosition:
{
data.enforceInterface(DESCRIPTOR);
de.tudarmstadt.kom.carsimulator.xml.route.KoordinatesWaypoint _result = this.getCurrentPosition();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getCurrentSpeed:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getCurrentSpeed();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_startSimulation:
{
data.enforceInterface(DESCRIPTOR);
this.startSimulation();
reply.writeNoException();
return true;
}
case TRANSACTION_pauseSimulation:
{
data.enforceInterface(DESCRIPTOR);
this.pauseSimulation();
reply.writeNoException();
return true;
}
case TRANSACTION_endSimulation:
{
data.enforceInterface(DESCRIPTOR);
this.endSimulation();
reply.writeNoException();
return true;
}
case TRANSACTION_setRoute:
{
data.enforceInterface(DESCRIPTOR);
de.tudarmstadt.kom.carsimulator.xml.route.Route _arg0;
if ((0!=data.readInt())) {
_arg0 = de.tudarmstadt.kom.carsimulator.xml.route.Route.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.setRoute(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isPaused:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isPaused();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isStarted:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isStarted();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getActiveElement:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getActiveElement();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getValue:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
double _result = this.getValue(_arg0);
reply.writeNoException();
reply.writeDouble(_result);
return true;
}
case TRANSACTION_getCurrentSpeedLimit:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getCurrentSpeedLimit();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements de.tudarmstadt.kom.carsimulator.SimulationEngineAPI
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public de.tudarmstadt.kom.carsimulator.xml.route.KoordinatesWaypoint getCurrentPosition() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
de.tudarmstadt.kom.carsimulator.xml.route.KoordinatesWaypoint _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCurrentPosition, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = de.tudarmstadt.kom.carsimulator.xml.route.KoordinatesWaypoint.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getCurrentSpeed() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCurrentSpeed, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void startSimulation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_startSimulation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void pauseSimulation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_pauseSimulation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void endSimulation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_endSimulation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean setRoute(de.tudarmstadt.kom.carsimulator.xml.route.Route pRoute) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((pRoute!=null)) {
_data.writeInt(1);
pRoute.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setRoute, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isPaused() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isPaused, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isStarted() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isStarted, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getActiveElement() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getActiveElement, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public double getValue(java.lang.String pPID) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
double _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pPID);
mRemote.transact(Stub.TRANSACTION_getValue, _data, _reply, 0);
_reply.readException();
_result = _reply.readDouble();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getCurrentSpeedLimit() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCurrentSpeedLimit, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getCurrentPosition = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getCurrentSpeed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_startSimulation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_pauseSimulation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_endSimulation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_setRoute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_isPaused = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_isStarted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getActiveElement = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getValue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getCurrentSpeedLimit = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
}
public de.tudarmstadt.kom.carsimulator.xml.route.KoordinatesWaypoint getCurrentPosition() throws android.os.RemoteException;
public int getCurrentSpeed() throws android.os.RemoteException;
public void startSimulation() throws android.os.RemoteException;
public void pauseSimulation() throws android.os.RemoteException;
public void endSimulation() throws android.os.RemoteException;
public boolean setRoute(de.tudarmstadt.kom.carsimulator.xml.route.Route pRoute) throws android.os.RemoteException;
public boolean isPaused() throws android.os.RemoteException;
public boolean isStarted() throws android.os.RemoteException;
public int getActiveElement() throws android.os.RemoteException;
public double getValue(java.lang.String pPID) throws android.os.RemoteException;
public int getCurrentSpeedLimit() throws android.os.RemoteException;
}
