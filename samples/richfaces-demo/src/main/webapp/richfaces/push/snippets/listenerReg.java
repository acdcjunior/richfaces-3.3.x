public void addListener(EventListener listener) {
synchronized (listener) {
	if (this.listener != listener) {
	this.listener = (PushEventListener) listener;
}
