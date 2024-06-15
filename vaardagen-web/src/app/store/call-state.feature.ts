import {signalStoreFeature, withState} from "@ngrx/signals";

export type CallState = "init" | "pending" | "success" | { error: string };

export function withCallState() {
  return signalStoreFeature(
    withState<{callState: CallState}>({callState: "init"}),
  );
}
