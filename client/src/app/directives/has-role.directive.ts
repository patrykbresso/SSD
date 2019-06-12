import { Directive, Input, TemplateRef, ViewContainerRef, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthStorageService } from '../auth/service/auth-storage.service';

@Directive({
  selector: '[appHasRole]'
})
export class HasRoleDirective implements OnInit, OnDestroy {

  private sub: Subscription;

  @Input('appHasRole') roles: string;

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private readonly authStorageService: AuthStorageService
  ) { }

  ngOnInit() {
    this.sub = this.authStorageService.currentRole.subscribe((role: string) => {
      if (this.roles.includes(role)) {
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
