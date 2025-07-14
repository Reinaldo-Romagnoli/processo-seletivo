import 'zone.js';
import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter }        from '@angular/router';
import { provideAnimations }    from '@angular/platform-browser/animations';
import { provideHttpClient }    from '@angular/common/http';
import { importProvidersFrom }  from '@angular/core';

// Módulos de formulário clássicos
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Modules do Angular Material
import { MatFormFieldModule }   from '@angular/material/form-field';
import { MatInputModule }       from '@angular/material/input';
import { MatIconModule }        from '@angular/material/icon';
import { MatButtonModule }      from '@angular/material/button';
import { MatSnackBarModule }    from '@angular/material/snack-bar';
import { MatTableModule }       from '@angular/material/table';
import { MatPaginatorModule }   from '@angular/material/paginator';

import { AppComponent } from './app/app.component';
import { appRoutes }    from './app/app-routing.module';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(appRoutes),
    provideAnimations(),
    provideHttpClient(),
    importProvidersFrom(
      FormsModule,
      ReactiveFormsModule,
      MatFormFieldModule,
      MatInputModule,
      MatIconModule,
      MatButtonModule,
      MatSnackBarModule,
      MatTableModule,
      MatPaginatorModule
    )
  ]
}).catch(err => console.error(err));