import { AuthStorageService } from './../auth/service/auth-storage.service';
import { Directive, Input, TemplateRef, ViewContainerRef, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

@Directive({
  selector: '[appIsAuthenticated]'
})
export class IsAuthenticatedDirective implements OnInit, OnDestroy {

  private sub: Subscription;

  @Input('appIsAuthenticated') condition: boolean;

  constructor(
    private readonly templateRef: TemplateRef<any>,
    private readonly viewContainer: ViewContainerRef,
    private readonly authStorageService: AuthStorageService
  ) { }

  ngOnInit() {
    this.sub = this.authStorageService.isAuthenticated.subscribe((isAuth: boolean) => {
      if ((isAuth && this.condition) || (!isAuth && !this.condition)) {
        this.viewContainer.createEmbeddedView(this.templateRef);
      } else {
        this.viewContainer.clear();
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
