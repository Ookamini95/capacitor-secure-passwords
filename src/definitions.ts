export interface SecurePasswordPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
